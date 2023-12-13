import mysql.connector
from bs4 import BeautifulSoup
import requests

db_config = {
    'host': 'localhost',
    'user': 'root',
    'password': '1234',
    'database': 'pcom',
}

news_num = 1743846

def crawl_news(news_id):
    url = f'https://www.gamemeca.com/view.php?gid={news_id}'

    response = requests.get(url)
    html = response.text

    soup = BeautifulSoup(html, 'html.parser')

    title_element = soup.find('div', class_='article-title')
    title = title_element.find('h1').text.strip() if title_element else None

    writer_element = soup.find('span', class_='writer')
    writer = writer_element.text.strip() if writer_element else None

    content_element = soup.find('div', class_='article')
    content = content_element.decode_contents(formatter="html") if content_element else None

    image_url = content_element.find('img')['src'] if content_element and content_element.find('img') else None

    return {
        'news_num': news_id,
        'news_title': title,
        'news_writer': writer,
        'news_content': content,
        'news_image': image_url,
    }

def insert_into_mysql(data):
    if data['news_title'] is not None and data['news_writer'] is not None and data['news_content'] is not None:
        try:
            connection = mysql.connector.connect(**db_config)
            cursor = connection.cursor()

            sql_query = """
                INSERT INTO news (news_title, news_writer, news_content, news_image)
                VALUES (%(news_title)s, %(news_writer)s, %(news_content)s, %(news_image)s)
            """

            cursor.execute(sql_query, data)
            connection.commit()

            print(f"뉴스 번호 {data['news_num']} 데이터 삽입 성공!")

        except Exception as e:
            print(f"오류 발생: {e}")

        finally:
            if connection.is_connected():
                cursor.close()
                connection.close()

for news_id in range(news_num-500, news_num+1):
    news_data = crawl_news(news_id)
    
    if all(news_data.values()):
        insert_into_mysql(news_data)
