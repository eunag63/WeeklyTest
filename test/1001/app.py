from flask import Flask, render_template, jsonify, request
from pymongo import MongoClient
import datetime

app = Flask(__name__)

client = MongoClient("mongodb://localhost:27017/")
db = client.dbStock


@app.route('/')
def index():
    return render_template('index.html')


@app.route('/post', methods=['POST'])
def save_post():
    title_receive = request.form['title_give']
    content_receive = request.form['content_give']
    print(title_receive, content_receive)

    for i in range(1, 100):
        doc = {
            'idx': i,
            'title': title_receive,
            'content': content_receive,
            'reg_date': datetime.datetime.utcnow(),
        }

        db.articles.insert_one(doc)

    return jsonify({'msg':'저장이 완료되었습니다.'})


@app.route('/post', methods=['GET'])
def get_post():
    articles = list(db.articles.find({}, {'_id': False}).sort('reg_date', 1))
    return jsonify({'all_articles':articles})


@app.route('/post', methods=['DELETE'])
def delete_post():
    idx_receive = request.form['idx_give']
    db.articles.delete_one({"idx": idx_receive})

    return jsonify({'msg':'삭제가 완료되었습니다.'})


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)