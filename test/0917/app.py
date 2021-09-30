from flask import Flask, render_template, jsonify, request

app = Flask(__name__)

import requests

from pymongo import MongoClient

client = MongoClient('localhost', 27017)
db = client.dbsparta


## HTML을 주는 부분
@app.route('/')
def home():
    return render_template('index.html')


@app.route('/cho', methods=['GET'])
def listing():
    choises = db.choise.distint("group")

    return jsonify({'each_chosies': choises})


## API 역할을 하는 부분
@app.route('/cho', methods=['POST'])
def write_review():
    first_receive = request.form['first_give']
    second_receive = request.form['second_give']

    doc = {
        'first': first_receive,
        'second': second_receive,
    }
    db.choise.insert_one(doc)

    return jsonify({'msg': '저장완료'})


if __name__ == '__main__':
    app.run('0.0.0.0', port=5000, debug=True)
