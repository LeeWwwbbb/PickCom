# -*- coding: utf-8 -*-
import json
import subprocess
import codecs  # 추가된 부분

def read_json(file_path):
    with open(file_path, 'r') as f:
        # Python 2에서는 유니코드 디코딩이 필요합니다.
        return json.load(f)

def write_json(file_path, data):
    with codecs.open(file_path, 'w', encoding='utf-8') as f:  # 수정된 부분
        # Python 2에서는 유니코드 인코딩이 필요합니다.
        json.dump(data, f, ensure_ascii=False, indent=4)

subprocess.call(["python", "Case.py"])
subprocess.call(["python", "Cooler.py"])
subprocess.call(["python", "CPU.py"])
subprocess.call(["python", "HDD.py"])
subprocess.call(["python", "MBoard.py"])
subprocess.call(["python", "Power.py"])
subprocess.call(["python", "RAM.py"])
subprocess.call(["python", "SSD.py"])
subprocess.call(["python", "VGA.py"])

# 제외할 항목
excluded_items = [u"HDD (NAS용)", u"쿼드로", u"고정핀/나사", u"VGA 지지대", u"써멀패드", u"SSD/HDD 주변기기", u"임베디드 보드", u"방열판", u"제온", u"중고",
                  u"VR 지원 장비", u"PowerLink", u"SLI Bridge", u"노트북", u"노트북용", u"전용 액세서리", u"외장그래픽 독",u"HDD (기업용)", u"HDD (CCTV용)", u"DC to DC", u"HDD (노트북용)",
                  u"중고 여부 확인요망", u"RAM 쿨러", u"구매 시 주의사항: 쿨링팬 수(선택), OC(선택), 채굴 여부 판매자 별도 문의 요망", u"가이드", u"서버용 파워", u"서버용",
                  u"수랭 부속품", u"오일", u"인텔(CPU내장)", u"조명기기", u"채굴용 케이스", u"케이블", u"튜닝 부속품", u"팬 부속품", u"팬컨트롤러", u"서버용 파워",
                  u"제품 상세 정보는 판매중인 쇼핑몰에서 반드시 확인하시기 바랍니다", u"DDR2", u"방열판 분류용 상품", u"메모리, 확장 슬롯, 오디오, 그래픽, USB 출력 별도 확인 요망",
                  u"상세스펙 판매처 별도 확인 요망", u"UPS", u"TFX 파워", u"DDR", u"시스템 쿨러", u"VGA 쿨러", u"M.2 SSD 쿨러", u"써멀 컴파운드", u"먼지필터", u"USB헤더 허브", u"더미램",
                  u"HDD 쿨러", u"HDD (리퍼비시)", u"SSHD (노트북용)", u"SSHD (PC용)", u"튜닝 케이스", u"랙마운트", u"H300~H750 허브랙 전용", u"HTPC 케이스", u"브라켓/가이드", u"전원부: 18+1+2페이즈",
                  u"메모리 DDR4 노트북용", u"인텔 B250", u"인텔 C252", u"PC케이스(RTX)", u"라이저 케이블", u"일반-ATX (30.5 x 22.5cm)", u"M-DTX (20.3x17.0cm)", u"LGA1155", u"메모리 규격: DDR3",
                  u"메모리 규격: DDR3, DDR2", u"메모리 규격: DDR2", u"커버/먼지필터", u"써멀 페이스트 가드"]
excluded_brands = [u"셀텍", u"모드컴", u"be", u"이도디스플레이", u"현대파워", u"SilverStone", u"HALO", u"BABEL", u"Enhance", u"Bestone", u"+PLUS", u"ANACOMDA", u"FOXCONN", u"EVERCOOL", u"NOCTUA"]
excluded_sockets = [u"AMD(소켓TR4)", u"AMD(소켓SP3)", u"AMD(소켓sTRX4)", u"인텔(소켓775)", u"인텔(소켓2011)", u"LGA2011", u"LGA2011-V3", u"LGA 1366", u"인텔(소켓1155)", u"소켓2011-V3", u"소켓 sTRX4", u"소켓 TR4", u"소켓1151v2", u"소켓sWRX8", u"AMD(소켓SP5)", u"인텔(소켓4677)", u"인텔(소켓4189)", u"인텔(소켓3647)", u"인텔(소켓2066)", u"산업용SSD", u"GDDR2(DDR2)", u"외장그래픽", u"노트북", u"DDR2", u"DDR3"]

json_files = [
    "HARDWARE_DATA_old/Case_List.json",
    "HARDWARE_DATA_old/Cooler_List.json",
    "HARDWARE_DATA_old/CPU_List.json",
    "HARDWARE_DATA_old/HDD_List.json",
    "HARDWARE_DATA_old/MBoard_List.json",
    "HARDWARE_DATA_old/Power_List.json",
    "HARDWARE_DATA_old/RAM_List.json",
    "HARDWARE_DATA_old/SSD_List.json",
    "HARDWARE_DATA_old/VGA_List.json"
]

# JSON 파일들을 읽어서 통합
for file in json_files:
    data = read_json(file)

    # "액세서리" 및 제외할 항목을 포함하지 않는 제품만 추가하고 저장
    filtered_data = [item for item in data if u"액세서리" not in item['spec']
                     and all(excluded_item not in item['spec'] for excluded_item in excluded_items)
                     and u"(중고)" not in item['name']
                     and u"중고" not in item['name']
                     and u"해외구매" not in item['name']
                     and u"병행수입" not in item['name']
                     and not any(u"칩:" in spec for spec in item['spec'])
                     and u"(콘로)" not in item['name']
                     and u"일시품절" not in item['price']
                     and u"가격비교예정" not in item['price']
                     and not any(word in u''.join(item.get('spec', [])) for word in excluded_sockets)
                     and all(excluded_brand not in item['brand'] for excluded_brand in excluded_brands)]

    # 저장할 파일 경로 생성
    save_path = file.replace("HARDWARE_DATA_old/", "HARDWARE_DATA_new/")

    if "Case_List" in file and any(u"GPU 장착:" in item['spec'] for item in data):
        filtered_data += [item for item in data if u"GPU 장착:" in item['spec']]

    # 필터링된 데이터를 파일로 저장
    write_json(save_path, filtered_data)
