from flask import Flask
from app.extensions import db
from app.Index import index
import click


def create_app():
    app = Flask(__name__)
    app.config.from_pyfile('settings.py')

    register_extensions(app)
    register_command(app)
    register_blueprint(app)


    return app

def register_extensions(app):
    db.init_app(app)

def register_blueprint(app):
    app.register_blueprint(index, url_prefix='/')


def register_command(app):

    @app.cli.command()
    def initdb():
        db.drop_all()
        db.create_all()
        click.echo('create success')