from app.Index import index
from app.models import User, File
from app.extensions import db, returnmessage, returnuser, random_filename, returnfile
from flask import jsonify, request, current_app
import os


@index.route('/register', methods=['POST'])
def register():
    username = request.form.get('username')
    password = request.form.get('password')
    user = User(username=username, password=password)
    db.session.add(user)
    db.session.commit()
    return jsonify(returnmessage(200, returnuser(user)))


@index.route('/login', methods=['POST'])
def login():
    username = request.form.get('username')
    password = request.form.get('password')
    user = User.query.filter(User.username == username).first()
    if user:
        if user.password == password:
            return jsonify(returnmessage(200, returnuser(user)))
        return jsonify(returnmessage(404, '密码错误'))
    else:
        return jsonify(returnmessage(404, 'none'))


@index.route('/upload', methods=['POST'])
def upload():
    files = request.files.getlist('file')
    num = 0
    filelist = []
    for file in files:
        num = num + 1
        file.filename = random_filename(file.filename)
        file.save(os.path.join(current_app.config['UPLOAD_PATH'], file.filename))
        file = File(filename=file.filename)
        db.session.add(file)
        db.session.commit()
        filelist.append(file)
    return jsonify(returnmessage(200, {
        'fileNum': num,
        'files': [returnfile(file) for file in filelist]
    }))
