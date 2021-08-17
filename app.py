from flask import Flask
import numpy as np
import pickle

app = Flask(__name__)
model = pickle.load(open('model.pkl','rb'))

@app.route("/")
#def home():
 #   return rander_template('index.html')
def index():
    return "Welcome To UK Accidents Prediction Matching Learning Modal"
