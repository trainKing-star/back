from flask_sqlalchemy import SQLAlchemy
from flask_script import Manager
import os
import uuid

db = SQLAlchemy()
manager = Manager()


def random_filename(filename):
    extension = os.path.splitext(filename)[1]
    new_filename = uuid.uuid4().hex + extension
    return new_filename


def returnmessage(id, kwargs):
    return {
        'messageid': id,
        'data': kwargs
    }

def returnuser(user):
    return {
        'userid': user.id,
        'username': user.username,
        'password': user.password,
        'createtime': user.create_time
    }