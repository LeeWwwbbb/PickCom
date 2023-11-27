import mysql.connector
import json

def get_data(cursor, Choice_Where_to_Use):
    default_query = """
    SELECT
        cpu_product_num, cpu_product_name, cpu_product_originalPrice, cpu_InterGrated_graphics,
        cpu_TDP, cpu_Stock_Cooler, cpu_Socket_Type, mboard_product_num, mboard_product_name,
        mboard_product_originalPrice, mboard_MBoard_Size, ram_product_num, ram_product_name,
        ram_product_originalPrice, ram_R_Size, cooler_product_num, cooler_product_name, cooler_product_originalPrice,
        cooler_Color
    FROM (
        SELECT
            cpu_product_num, cpu_product_name, cpu_product_originalPrice, cpu_InterGrated_graphics,
            cpu_TDP, cpu_Stock_Cooler, cpu_Socket_Type, mboard_product_num, mboard_product_name,
            mboard_product_originalPrice, mboard_MBoard_Size, ram_product_num, ram_product_name,
            ram_product_originalPrice, ram_R_Size, cooler_product_num, cooler_product_name, cooler_product_originalPrice,
        cooler_Color,
            ROW_NUMBER() OVER (PARTITION BY cpu_product_num ORDER BY cpu_product_num) AS rn
        FROM pc_default
    ) AS subquery
    WHERE rn <= 20;
    """
    cursor.execute(default_query)
    default_data = cursor.fetchall()

    money_ranges = [600000, 700000, 800000, 1000000, 1300000, 2000000, 2400000, 3000000]
    #50~60, 70~80, 90~110, 120~140, 180~200, 220~260, 270~330

    if Choice_Where_to_Use == 'S':
        # ssd 정보 가져오기
        ssd_query = "SELECT product_num, product_name, product_originalPrice FROM pc_ssd WHERE S_size IN ('250GB', '256GB') ORDER BY product_num LIMIT 10"
        cursor.execute(ssd_query)
        S_ssd_data = cursor.fetchall()
        # power 정보 가져오기
        power_query = "SELECT product_num, product_name, product_originalPrice, W FROM pc_power ORDER BY product_num LIMIT 200"
        cursor.execute(power_query)
        S_power_data = cursor.fetchall()
        # case 정보 가져오기
        case_query = "SELECT product_num, product_name, product_originalPrice, Board_Size, GPU_Size, Color FROM pc_case ORDER BY product_num LIMIT 30"
        cursor.execute(case_query)
        S_case_data = cursor.fetchall()

    if Choice_Where_to_Use == 'G':
        # ssd 정보 가져오기
        G_ssd_query = "SELECT product_num, product_name, product_originalPrice FROM pc_ssd WHERE S_size IN ('1TB') ORDER BY product_num LIMIT 10"
        cursor.execute(G_ssd_query)
        G_ssd_data = cursor.fetchall()
        # vga 정보 가져오기
        G_vga_query = "SELECT product_num, product_name, product_originalPrice, VGA_Name, VGA_Size, TDP, Max_Used_W, Boost_Clock FROM pc_vga WHERE (VGA_Name, product_num) IN (SELECT VGA_Name, product_num FROM (SELECT VGA_Name, product_num, ROW_NUMBER() OVER (PARTITION BY VGA_Name ORDER BY product_num) as row_num FROM pc_vga) ranked WHERE row_num <= 2) ORDER BY product_num;"
        cursor.execute(G_vga_query)
        G_vga_data = cursor.fetchall()
        # power 정보 가져오기
        G_power_query = "SELECT product_num, product_name, product_originalPrice, W FROM pc_power ORDER BY product_num LIMIT 200"
        cursor.execute(G_power_query)
        G_power_data = cursor.fetchall()
        # case 정보 가져오기
        G_case_query = "SELECT product_num, product_name, product_originalPrice, Board_Size, GPU_Size, Color FROM pc_case ORDER BY product_num LIMIT 30"
        cursor.execute(G_case_query)
        G_case_data = cursor.fetchall()

    for money_range in money_ranges:
        G_result_list = []
        S_result_list = []
        if Choice_Where_to_Use == 'S':
            for default in default_data:
                cpu_product_num = default[0]
                cpu_product_name = default[1]
                cpu_product_originalPrice = default[2]
                cpu_InterGrated_graphics = default[3]
                cpu_TDP = default[4]
                cpu_Stock_Cooler = default[5]
                mboard_product_num = default[7]
                mboard_product_name = default[8]
                mboard_product_originalPrice = default[9]
                mboard_MBoard_Size = default[10]
                ram_product_num = default[11]
                ram_product_name = default[12]
                ram_product_originalPrice = default[13]
                ram_R_Size = default[14]
                cooler_product_num = default[15]
                cooler_product_name = default[16]
                cooler_product_originalPrice = default[17]
                cooler_Color = default[18]
                if cpu_TDP < 150:
                    cpu_TDP = 200
                for pc_power in S_power_data:
                    power_rec = pc_power[0]
                    power_name = pc_power[1]
                    power_price = int(pc_power[2])
                    W = pc_power[3]
                    if (cpu_TDP + 10) <= W <= (cpu_TDP + 10 + 120):
                        if cpu_InterGrated_graphics == "탑재":
                            for pc_case in S_case_data:
                                case_rec = pc_case[0]
                                case_name = pc_case[1]
                                case_price = int(pc_case[2])
                                Board_Size = pc_case[3]
                                case_Color = pc_case[5]
                                if Board_Size == mboard_MBoard_Size:
                                    for pc_ssd in S_ssd_data:
                                        ssd_rec = pc_ssd[0]
                                        ssd_name = pc_ssd[1]
                                        ssd_price = int(pc_ssd[2])
                                        if cpu_Stock_Cooler == "미포함":
                                            Cool_in_TotalPrice = int(cpu_product_originalPrice) + int(mboard_product_originalPrice) + int(ram_product_originalPrice) + int(cooler_product_originalPrice) + power_price + case_price + ssd_price
                                            if money_range * 0.95 <= Cool_in_TotalPrice <= money_range * 1.05:
                                                recommend_Val = int(
                                                    cpu_product_num + mboard_product_num + ram_product_num
                                                    + cooler_product_num + ssd_rec + power_rec
                                                    + case_rec) / 7
                                                if recommend_Val <= 30:
                                                    S_result_list.append({
                                                        "CPU": cpu_product_name,
                                                        "MBoard": mboard_product_name,
                                                        "RAM": ram_product_name,
                                                        "RAMSize": ram_R_Size,
                                                        "SSD": ssd_name,
                                                        "Power": power_name,
                                                        "Cooler": cooler_product_name,
                                                        "Cooler_Color": cooler_Color,
                                                        "Case": case_name,
                                                        "Case_Color": case_Color,
                                                        "TotalPrice": Cool_in_TotalPrice,
                                                        "추천도": recommend_Val
                                                    })
                                                if len(S_result_list) >= 100:
                                                    break
                                        else:
                                            Cool_out_TotalPrice = int(cpu_product_originalPrice) + int(mboard_product_originalPrice) + int(ram_product_originalPrice) + power_price + case_price + ssd_price
                                            if money_range * 0.9 <= Cool_out_TotalPrice <= money_range * 1.1:
                                                recommend_Val = int(cpu_product_num + mboard_product_num + ram_product_num + ssd_rec + power_rec + case_rec) / 6
                                                if recommend_Val <= 30:
                                                    S_result_list.append({
                                                        "CPU": cpu_product_name,
                                                        "MBoard": mboard_product_name,
                                                        "RAM": ram_product_name,
                                                        "RAMSize": ram_R_Size,
                                                        "SSD": ssd_name,
                                                        "Power": power_name,
                                                        "Cooler": "기본쿨러",
                                                        "Case": case_name,
                                                        "Case_Color": case_Color,
                                                        "TotalPrice": Cool_out_TotalPrice,
                                                        "추천도": recommend_Val
                                                    })
                                                if len(S_result_list) >= 100:
                                                    break
            # S_result_list에 상위 10개 정보 추가
            S_result_list.sort(key=lambda x: x['추천도'], reverse=False)
            top_10_S_results = S_result_list[:10]

            # JSON 파일에 상위 10개 항목 저장
            with open(f'Samu/S_result_{int(money_range/100000)}.json', 'w', encoding='utf-8') as json_file:
                json.dump(top_10_S_results, json_file, indent=2, ensure_ascii=False)

        added_vga_count = {}
        if Choice_Where_to_Use == 'G':
            for default in default_data:
                cpu_product_num = default[0]
                cpu_product_name = default[1]
                cpu_product_originalPrice = default[2]
                cpu_TDP = default[4]
                cpu_Stock_Cooler = default[5]
                cpu_Socket_Type = default[6]
                mboard_product_num = default[7]
                mboard_product_name = default[8]
                mboard_product_originalPrice = default[9]
                mboard_MBoard_Size = default[10]
                ram_product_num = default[11]
                ram_product_name = default[12]
                ram_product_originalPrice = default[13]
                ram_R_Size = default[14]
                cooler_product_num = default[15]
                cooler_product_name = default[16]
                cooler_product_originalPrice = default[17]
                cooler_Color = default[18]
                if ram_R_Size <= 8:
                    continue
                if cpu_TDP < 150:
                    cpu_TDP = 200
                for pc_vga in G_vga_data:
                    # 이미 4번 이상 추가된 VGA이면 건너뛰기
                    if pc_vga[0] in added_vga_count and added_vga_count[pc_vga[0]] >= 4:
                        continue
                    vga_rec = pc_vga[0]
                    vga_name = pc_vga[1]
                    vga_price = int(pc_vga[2])
                    vga_Size = float(pc_vga[4])
                    vga_TDP = int(pc_vga[5])
                    vga_Max_W = int(pc_vga[6])
                    if vga_price > money_range - cpu_product_originalPrice - mboard_product_originalPrice:
                        continue
                    for pc_power in G_power_data:
                        power_rec = pc_power[0]
                        power_name = pc_power[1]
                        power_price = int(pc_power[2])
                        W = pc_power[3]
                        if vga_TDP == 0 and (cpu_TDP + vga_TDP) < vga_Max_W:
                            Used_W = vga_Max_W
                        else:
                            Used_W = cpu_TDP + vga_TDP
                        if Used_W < W < Used_W + 130:
                            for pc_case in G_case_data:
                                case_rec = pc_case[0]
                                case_name = pc_case[1]
                                case_price = int(pc_case[2])
                                case_Mboard_Size = pc_case[3]
                                case_vga_Size = float(pc_case[4])
                                case_Color = pc_case[5]
                                if case_vga_Size >= vga_Size and case_Mboard_Size == mboard_MBoard_Size:
                                    for pc_ssd in G_ssd_data:
                                        ssd_rec = pc_ssd[0]
                                        ssd_name = pc_ssd[1]
                                        ssd_price = int(pc_ssd[2])
                                        if cpu_Stock_Cooler == "미포함":
                                            Cool_in_TotalPrice = int(cpu_product_originalPrice) + int(mboard_product_originalPrice) + int(ram_product_originalPrice) + int(cooler_product_originalPrice) + int(pc_vga[2]) + power_price + case_price + ssd_price
                                            if money_range * 0.95 <= Cool_in_TotalPrice <= money_range * 1.05:
                                                recommend_Val = int(
                                                    cpu_product_num + mboard_product_num + ram_product_num
                                                    + cooler_product_num + ssd_rec + power_rec
                                                    + case_rec + vga_rec) / 8
                                                if recommend_Val <= 30:
                                                    G_result_list.append({
                                                        "CPU": cpu_product_name,
                                                        "MBoard": mboard_product_name,
                                                        "RAM": ram_product_name,
                                                        "RAMSize": ram_R_Size,
                                                        "VGA": vga_name,
                                                        "SSD": ssd_name,
                                                        "Power": power_name,
                                                        "Cooler": cooler_product_name,
                                                        "Cooler_Color": cooler_Color,
                                                        "Case": case_name,
                                                        "Case_Color": case_Color,
                                                        "TotalPrice": Cool_in_TotalPrice,
                                                        "추천도": recommend_Val
                                                    })

                                                    added_vga_count[pc_vga[0]] = len(G_result_list) - 1
                                                    if len(added_vga_count) >= 100 or len(added_vga_count) >= len(G_vga_data):
                                                        break
                                                if len(G_result_list) >= 100:
                                                    break
                                        else:
                                            Cool_out_TotalPrice = int(cpu_product_originalPrice) + int(mboard_product_originalPrice) + int(ram_product_originalPrice) + int(pc_vga[2]) + power_price + case_price + ssd_price
                                            if money_range * 0.9 <= Cool_out_TotalPrice <= money_range * 1.1:
                                                recommend_Val = int(
                                                    cpu_product_num + mboard_product_num + ram_product_num
                                                    + ssd_rec + power_rec
                                                    + case_rec + vga_rec) / 7
                                                if recommend_Val <= 30:
                                                    G_result_list.append({
                                                        "CPU": cpu_product_name,
                                                        "MBoard": mboard_product_name,
                                                        "RAM": ram_product_name,
                                                        "RAMSize": ram_R_Size,
                                                        "VGA": vga_name,
                                                        "SSD": ssd_name,
                                                        "Power": power_name,
                                                        "Cooler": "기본쿨러",
                                                        "Case": case_name,
                                                        "Case_Color": case_Color,
                                                        "TotalPrice": Cool_out_TotalPrice,
                                                        "추천도": recommend_Val
                                                    })
                                                if len(G_result_list) >= 100:
                                                    break
            # G_result_list에 상위 10개 정보 추가
            G_result_list.sort(key=lambda x: x['추천도'], reverse=False)
            # 중복을 방지하기 위해 이미 추가된 VGA를 추적하는 딕셔너리 초기화
            added_vga_count = {}
            # 정렬된 리스트에서 중복을 방지하면서 상위 10개만 유지
            G_result_list = [vga for vga in G_result_list if vga['VGA'] not in added_vga_count and not added_vga_count.update({vga['VGA']: True})][:10]

            # JSON 파일에 상위 10개 항목 저장
            with open(f'Game/G_result_{int(money_range/100000)}.json', 'w', encoding='utf-8') as json_file:
                json.dump(G_result_list, json_file, indent=2, ensure_ascii=False)

# DB 설정
db_config = {
    'host': 'localhost',
    'user': 'root',
    'password': '1234',
    'database': 'pcom'
}
connection = mysql.connector.connect(**db_config)
cursor = connection.cursor()


for Choice_Where_to_Use in ["S", "G"]:
    get_data(cursor, Choice_Where_to_Use)