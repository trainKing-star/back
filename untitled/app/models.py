from app import db
from datetime import datetime

class User(db.Model):
    __tablename__ = "user"
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(10))
    password = db.Column(db.String(20))
    create_time = db.Column(db.DateTime, default=datetime.now, index=True)