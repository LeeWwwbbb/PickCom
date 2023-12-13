import mysql.connector
from bs4 import BeautifulSoup
import requests
import random

db_config = {
    'host': 'localhost',
    'user': 'root',
    'password': '1234',
    'database': 'pcom',
}

board_num = 344330

def crawl_board(board_id):
    url = f'https://www.inven.co.kr/board/it/2631/{board_id}'

    response = requests.get(url)
    html = response.text

    soup = BeautifulSoup(html, 'html.parser')

    title_element = soup.find('div', class_='articleTitle')
    title = title_element.find('h1').text.strip() if title_element else None

    date_element = soup.find('div', class_='articleDate')
    date = date_element.text.strip() if date_element else None

    content_element = soup.find('div', id='powerbbsContent')
    content = content_element.get_text("\n", strip=True) if content_element else None
    content = "<br>".join(content.split("\n")) if content else None

    categories = ['free', 'question', 'image', 'review']
    board_cate = random.choice(categories)

    member_num = random.randint(13, 112)

    board_visitCount = random.randint(5, 97)

    return {
        'board_num': board_id,
        'member_num': member_num,
        'board_title': title,
        'board_createdDate': date,
        'board_content': content,
        'board_cate': board_cate,
        'board_visitCount' : board_visitCount
    }

def insert_into_mysql(data):
        try:
            connection = mysql.connector.connect(**db_config)
            cursor = connection.cursor()

            sql_query = """
              INSERT INTO board (board_num, member_num, board_title, board_createdDate, board_content, board_cate, board_visitCount)
              VALUES (%(board_num)s, %(member_num)s, %(board_title)s, %(board_createdDate)s, %(board_content)s, %(board_cate)s, %(board_visitCount)s)
            """

            cursor.execute(sql_query, data)
            connection.commit()

            print(f" 번호 {data['board_num']} 데이터 삽입 성공!")

        except Exception as e:
            print(f"오류 발생: {e}")

        finally:
            if connection.is_connected():
                cursor.close()
                connection.close()

for board_id in range(board_num-300, board_num+1):
    board_data = crawl_board(board_id)

    if all(board_data.values()):
        insert_into_mysql(board_data)
