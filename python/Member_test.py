import mysql.connector
import random
import string
import MemberList

db_config = {
    'host': 'localhost',
    'user': 'root',
    'password': '1234',
    'database': 'pcom',
}


random_names = MemberList.korean_names_chars.copy()

def generate_random_string(length):
    letters = string.ascii_letters + string.digits
    return ''.join(random.choice(letters) for i in range(length))

def generate_random_email():
    email_provider = ['gmail.com', 'yahoo.com', 'outlook.com', 'hotmail.com']
    return generate_random_string(8) + '@' + random.choice(email_provider)

def insert_random_member_data(insert_count):
    try:
        connection = mysql.connector.connect(**db_config)
        cursor = connection.cursor()

        sql_query = """
            INSERT INTO member (member_id, member_name, member_nickName, member_passwd, member_email, member_rank, member_delete)
            VALUES (%s, %s, %s, %s, %s, %s, %s)
        """

        for _ in range(insert_count):
            member_id = generate_random_string(10)
            member_name = random.choice(random_names)
            random_names.remove(member_name)
            member_nickName = member_name
            member_passwd = generate_random_string(12)
            member_email = generate_random_email()
            member_rank = random.randint(1, 10)
            member_delete = 0  # False로 표시

            cursor.execute(sql_query, (member_id, member_name, member_nickName, member_passwd, member_email, member_rank, member_delete))
            connection.commit()

        print(f"{insert_count}개의 랜덤 데이터 삽입 완료!")

    except Exception as e:
        print(f"오류 발생: {e}")

    finally:
        if connection.is_connected():
            cursor.close()
            connection.close()

insert_count = 100
insert_random_member_data(insert_count)
