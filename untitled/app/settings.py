import os

SQLALCHEMY_DATABASE_URI = "mysql+pymysql://root:123456@127.0.0.1:3306/data?charset=utf8"

JSON_AS_ASCII = False

SQLALCHEMY_TRACK_MODIFICATIONS = False

UPLOAD_PATH = os.path.join(os.path.join(os.path.dirname(__file__), 'uploads'))
