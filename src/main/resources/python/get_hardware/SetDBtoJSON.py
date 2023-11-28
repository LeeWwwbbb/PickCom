# -*- coding: utf-8 -*-

import json
import mysql.connector
import re

# MariaDB 연결 정보
db_config = {
    'host': 'localhost',
    'user': 'root',
    'password': '1234',
    'database': 'pcom'
}
connection = mysql.connector.connect(**db_config)
cursor = connection.cursor()

with open('HARDWARE_DATA_new/Case_List.json', 'r') as f:
    case_data = json.load(f)
with open('HARDWARE_DATA_new/Cooler_List.json', 'r') as f:
    cooler_data = json.load(f)
with open('HARDWARE_DATA_new/CPU_List.json', 'r') as f:
    cpu_data = json.load(f)
with open('HARDWARE_DATA_new/HDD_List.json', 'r') as f:
    hdd_data = json.load(f)
with open('HARDWARE_DATA_new/MBoard_List.json', 'r') as f:
    mboard_data = json.load(f)
with open('HARDWARE_DATA_new/Power_List.json', 'r') as f:
    power_data = json.load(f)
with open('HARDWARE_DATA_new/RAM_List.json', 'r') as f:
    ram_data = json.load(f)
with open('HARDWARE_DATA_new/SSD_List.json', 'r') as f:
    ssd_data = json.load(f)
with open('HARDWARE_DATA_new/VGA_List.json', 'r') as f:
    vga_data = json.load(f)

delete_query1 = "DELETE FROM pc_case"
delete_query2 = "DELETE FROM pc_cooler"
delete_query3 = "DELETE FROM pc_cpu"
delete_query4 = "DELETE FROM pc_hdd"
delete_query5 = "DELETE FROM pc_mboard"
delete_query6 = "DELETE FROM pc_power"
delete_query7 = "DELETE FROM pc_ram"
delete_query8 = "DELETE FROM pc_ssd"
delete_query9 = "DELETE FROM pc_vga"
delete_query10 = "DELETE FROM pc_default"

cursor.execute(delete_query1)
cursor.execute(delete_query2)
cursor.execute(delete_query3)
cursor.execute(delete_query4)
cursor.execute(delete_query5)
cursor.execute(delete_query6)
cursor.execute(delete_query7)
cursor.execute(delete_query8)
cursor.execute(delete_query9)
cursor.execute(delete_query10)

alter_query = "ALTER TABLE pc_case AUTO_INCREMENT = 1"
cursor.execute(alter_query)
alter_query = "ALTER TABLE pc_cooler AUTO_INCREMENT = 1"
cursor.execute(alter_query)
alter_query = "ALTER TABLE pc_cpu AUTO_INCREMENT = 1"
cursor.execute(alter_query)
alter_query = "ALTER TABLE pc_hdd AUTO_INCREMENT = 1"
cursor.execute(alter_query)
alter_query = "ALTER TABLE pc_mboard AUTO_INCREMENT = 1"
cursor.execute(alter_query)
alter_query = "ALTER TABLE pc_power AUTO_INCREMENT = 1"
cursor.execute(alter_query)
alter_query = "ALTER TABLE pc_ram AUTO_INCREMENT = 1"
cursor.execute(alter_query)
alter_query = "ALTER TABLE pc_ssd AUTO_INCREMENT = 1"
cursor.execute(alter_query)
alter_query = "ALTER TABLE pc_vga AUTO_INCREMENT = 1"
cursor.execute(alter_query)
insert_default_cooler = "INSERT INTO pc_cooler (product_num, manufacturer_name, product_name, product_salePrice, product_originalPrice, Socket_Type, Color, product_description, product_IMG) VALUES (0, '기본쿨러', '기본쿨러', '0', '0', '기본', NULL, NULL, NULL)"
cursor.execute(insert_default_cooler)

for pc_case in case_data:
    manufacturer_name = pc_case.get('brand')
    product_name = pc_case.get('name')
    product_price = pc_case.get('price')

    if product_price == '일시품절':
        product_originalPrice = 0
        product_salePrice = 0  # '일시품절'인 경우 0으로 할당
    elif product_price == '가격비교예정':
        product_originalPrice = 0
        product_salePrice = 0  # '가격비교예정'인 경우 0으로 할당
    elif product_price == '가격비교중지':
        product_originalPrice = 0
        product_salePrice = 0  # '가격비교예정'인 경우 0으로 할당
    else:
        product_originalPrice = int(product_price)
        product_salePrice = int(product_price) * 0.95

    product_description = ';'.join(pc_case.get('spec'))
    Size = pc_case.get('spec')[0]
    Color = pc_case.get('color_text')
    matches = re.search(r'\(([^)]+)\)', Size)
    if matches:
        C_Size = matches.group(1)
    else:
        C_Size = Size

    match_gpu1 = re.search(r'VGA 장착 길이: (?:최대 )?(\d+)mm', product_description)
    match_gpu2 = re.search(r'VGA 장착 길이: (\d+)~(\d+)mm', product_description)
    if match_gpu1:
        gpu_size = match_gpu1.group(1)
    elif match_gpu2:
        gpu_size = match_gpu2.group(2)
    else:
        gpu_size = 0
    product_img = pc_case.get('img')
    if gpu_size != 0:
        insert_query = "INSERT INTO pc_case(manufacturer_name, product_name, product_salePrice, product_originalPrice, Board_Size, GPU_Size, Color, product_description, product_IMG) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)"
        cursor.execute(insert_query, (
            manufacturer_name, product_name, product_salePrice, product_originalPrice, C_Size, gpu_size, Color,
            product_description, product_img))

for cooler in cooler_data:
    manufacturer_name = cooler.get('brand')
    product_name = cooler.get('name')
    product_price = cooler.get('price')

    if product_price == '일시품절':
        product_originalPrice = 0
        product_salePrice = 0  # '일시품절'인 경우 0으로 할당
    elif product_price == '가격비교예정':
        product_originalPrice = 0
        product_salePrice = 0  # '가격비교예정'인 경우 0으로 할당
    elif product_price == '가격비교중지':
        product_originalPrice = 0
        product_salePrice = 0  # '가격비교예정'인 경우 0으로 할당
    else:
        product_originalPrice = int(product_price)
        product_salePrice = int(product_price) * 0.95

    product_description = ';'.join(cooler.get('spec'))
    socket_type = cooler.get('spec')[0]
    product_img = cooler.get('img')
    insert_query = "INSERT INTO pc_cooler(manufacturer_name, product_name, product_salePrice, product_originalPrice, Socket_Type, product_description, product_IMG) VALUES (%s, %s, %s, %s, %s, %s, %s)"
    cursor.execute(insert_query,
                   (manufacturer_name, product_name, product_salePrice, product_originalPrice, socket_type,
                    product_description, product_img))

for cpu in cpu_data:
    manufacturer_name = cpu.get('brand')
    product_name = cpu.get('name')
    product_price = cpu.get('price')

    if product_price == '일시품절':
        product_originalPrice = 0
        product_salePrice = 0  # '일시품절'인 경우 0으로 할당
    elif product_price == '가격비교예정':
        product_originalPrice = 0
        product_salePrice = 0  # '가격비교예정'인 경우 0으로 할당
    elif product_price == '가격비교중지':
        product_originalPrice = 0
        product_salePrice = 0  # '가격비교예정'인 경우 0으로 할당
    else:
        product_originalPrice = int(product_price)
        product_salePrice = int(product_price) * 0.95

    product_description = ';'.join(cpu.get('spec'))
    socket_type = cpu.get('spec')[0]
    product_img = cpu.get('img')
    insert_query = "INSERT INTO pc_cpu(manufacturer_name, product_name, product_salePrice, product_originalPrice, Socket_Type, product_description, product_IMG) VALUES (%s, %s, %s, %s, %s, %s, %s)"
    cursor.execute(insert_query,
                   (manufacturer_name, product_name, product_salePrice, product_originalPrice, socket_type,
                    product_description, product_img))

for hdd in hdd_data:
    manufacturer_name = hdd.get('brand')
    product_name = hdd.get('name')
    product_price = hdd.get('price')

    if product_price == '일시품절':
        product_originalPrice = 0
        product_salePrice = 0  # '일시품절'인 경우 0으로 할당
    elif product_price == '가격비교예정':
        product_originalPrice = 0
        product_salePrice = 0  # '가격비교예정'인 경우 0으로 할당
    elif product_price == '가격비교중지':
        product_originalPrice = 0
        product_salePrice = 0  # '가격비교예정'인 경우 0으로 할당
    else:
        product_originalPrice = int(product_price)
        product_salePrice = int(product_price) * 0.95

    product_description = ';'.join(hdd.get('spec'))
    capacity = hdd.get('spec')[0]
    form_factor = hdd.get('spec')[2]
    rpm = hdd.get('spec')[3]
    product_img = hdd.get('img')
    insert_query = "INSERT INTO pc_hdd(manufacturer_name, product_name, product_salePrice, product_originalPrice, Capacity, Form_Factor, RPM, product_description, product_IMG) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)"
    cursor.execute(insert_query,
                   (manufacturer_name, product_name, product_salePrice, product_originalPrice, capacity, form_factor,
                    rpm, product_description, product_img))

for mboard in mboard_data:
    manufacturer_name = mboard.get('brand')
    product_name = mboard.get('name')
    product_price = mboard.get('price')

    if product_price == '일시품절':
        product_originalPrice = 0
        product_salePrice = 0  # '일시품절'인 경우 0으로 할당
    elif product_price == '가격비교예정':
        product_originalPrice = 0
        product_salePrice = 0  # '가격비교예정'인 경우 0으로 할당
    elif product_price == '가격비교중지':
        product_originalPrice = 0
        product_salePrice = 0  # '가격비교예정'인 경우 0으로 할당
    else:
        product_originalPrice = int(product_price)
        product_salePrice = int(product_price) * 0.95

    product_description = ';'.join(mboard.get('spec'))
    socket_type = mboard.get('spec')[1]
    form_factor = mboard.get('spec')[2]
    product_img = mboard.get('img')
    insert_query = "INSERT INTO pc_mboard(manufacturer_name, product_name, product_salePrice, product_originalPrice, Socket_Type, Form_Factor, product_description, product_IMG) VALUES (%s, %s, %s, %s, %s, %s, %s, %s)"
    cursor.execute(insert_query,
                   (manufacturer_name, product_name, product_salePrice, product_originalPrice, socket_type, form_factor,
                    product_description, product_img))

for power in power_data:
    manufacturer_name = power.get('brand')
    product_name = power.get('name')
    product_price = power.get('price')

    if product_price == '일시품절':
        product_originalPrice = 0
        product_salePrice = 0  # '일시품절'인 경우 0으로 할당
    elif product_price == '가격비교예정':
        product_originalPrice = 0
        product_salePrice = 0  # '가격비교예정'인 경우 0으로 할당
    elif product_price == '가격비교중지':
        product_originalPrice = 0
        product_salePrice = 0  # '가격비교예정'인 경우 0으로 할당
    else:
        product_originalPrice = int(product_price)
        product_salePrice = int(product_price) * 0.95

    product_description = ';'.join(pc_power.get('spec'))
    board_size = product_description.split(";")
    Watt = board_size[1]
    W = re.findall(r'\d+', Watt)
    W_str = ''.join(W)

    product_img = pc_power.get('img')

    insert_query = "INSERT INTO pc_power(manufacturer_name, product_name, product_salePrice, product_originalPrice, W, product_description, product_IMG) VALUES (%s, %s, %s, %s, %s, %s, %s)"
    insert_value = (manufacturer_name, product_name, product_salePrice, product_originalPrice, W_str, product_description, product_img)
    cursor.execute(insert_query, insert_value)

for pc_ram in ram_data:
    manufacturer_name = pc_ram.get('brand')
    product_name = pc_ram.get('name')
    product_price = pc_ram.get('price')

    if product_price == '일시품절':
        product_originalPrice = 0
        product_salePrice = 0  # '일시품절'인 경우 0으로 할당
    elif product_price == '가격비교예정':
        product_originalPrice = 0
        product_salePrice = 0  # '가격비교예정'인 경우 0으로 할당
    elif product_price == '가격비교중지':
        product_originalPrice = 0
        product_salePrice = 0  # '가격비교예정'인 경우 0으로 할당
    else:
        product_originalPrice = int(product_price)
        product_salePrice = int(product_price) * 0.95

    product_description = ';'.join(pc_ram.get('spec'))

    size = pc_ram.get('size')
    Version = pc_ram.get('spec')[1]
    ram_type = pc_ram.get('spec')[2]
    getMHz = r'(\d+MHz)'
    matchs = re.search(getMHz, ram_type)
    if matchs:
        MHz_raw = matchs.group(1)
        MHz = int(re.search(r'\d+', MHz_raw).group())

    product_img = pc_ram.get('img')

    insert_query = "INSERT INTO pc_ram(manufacturer_name, product_name, product_salePrice, product_originalPrice, R_Size, Version, MHz, product_description, product_IMG) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)"
    insert_value = (manufacturer_name, product_name, product_salePrice, product_originalPrice, size, Version, MHz, product_description, product_img)
    cursor.execute(insert_query, insert_value)

for pc_ssd in ssd_data:
    manufacturer_name = pc_ssd.get('brand')
    product_name = pc_ssd.get('name')
    product_price = pc_ssd.get('price')

    if product_price == '일시품절':
        product_originalPrice = 0
        product_salePrice = 0  # '일시품절'인 경우 0으로 할당
    elif product_price == '가격비교예정':
        product_originalPrice = 0
        product_salePrice = 0  # '가격비교예정'인 경우 0으로 할당
    elif product_price == '가격비교중지':
        product_originalPrice = 0
        product_salePrice = 0  # '가격비교예정'인 경우 0으로 할당
    else:
        product_originalPrice = int(product_price)
        product_salePrice = int(product_price) * 0.95

    product_description = ';'.join(pc_ssd.get('spec'))
    ssd_size = ''.join(pc_ssd.get('size'))

    product_img = pc_ssd.get('img')

    insert_query = "INSERT INTO pc_ssd(manufacturer_name, product_name, product_salePrice, product_originalPrice, S_Size, product_description, product_IMG) VALUES (%s, %s, %s, %s, %s, %s, %s)"
    insert_value = (manufacturer_name, product_name, product_salePrice, product_originalPrice, ssd_size, product_description, product_img)
    cursor.execute(insert_query, insert_value)

for pc_vga in vga_data:
    manufacturer_name = pc_vga.get('brand')
    product_name = pc_vga.get('name')
    product_price = pc_vga.get('price')

    if product_price == '일시품절':
        product_originalPrice = 0
        product_salePrice = 0  # '일시품절'인 경우 0으로 할당
    elif product_price == '가격비교예정':
        product_originalPrice = 0
        product_salePrice = 0  # '가격비교예정'인 경우 0으로 할당
    elif product_price == '가격비교중지':
        product_originalPrice = 0
        product_salePrice = 0  # '가격비교예정'인 경우 0으로 할당
    else:
        product_originalPrice = int(product_price)
        product_salePrice = int(product_price) * 0.95

    product_description = ';'.join(pc_vga.get('spec'))
    useW = r'사용전력: (\d+)W'
    max_useW = r'사용전력: 최대 (\d+)W'
    matches = re.search(useW, product_description)
    if matches:
        TDP = matches.group(1)
    else:
        matches_max = re.search(max_useW, product_description)
        if matches_max:
            TDP = matches_max.group(1)
        else:
            TDP = 0

    maxW = r'정격파워 (\d+)W'
    matches2 = re.search(maxW, product_description)
    if matches2:
        Max_Used = matches2.group(1)
    else:
        Max_Used = 0

    match_length = re.search(r'가로\(길이\): (\d+(\.\d+)?)mm', product_description)
    if match_length:
        VGA_size = match_length.group(1)
    else:
        VGA_size = 0

    VGA_Name = pc_vga.get('spec')[0]
    product_img = pc_vga.get('img')

    boost_clock_match = re.search(r'부스트클럭: (\d+)MHz', product_description)
    if boost_clock_match:
        boost_clock = boost_clock_match.group(1)

    if TDP != 0 or Max_Used != 0:
        insert_query = "INSERT INTO pc_vga(manufacturer_name, product_name, product_salePrice, product_originalPrice, VGA_Name, VGA_Size, TDP, Max_Used_W, Boost_Clock, product_description, product_IMG) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"
        insert_value = (manufacturer_name, product_name, product_salePrice, product_originalPrice, VGA_Name, VGA_size, TDP, Max_Used, boost_clock, product_description, product_img)
        cursor.execute(insert_query, insert_value)
connection.commit()
connection.close()