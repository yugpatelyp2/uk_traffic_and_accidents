from Flask import Flask,render_template
import joblib
from Flask import request
from Flask import jsonify
import numpy as np
import os

app = Flask(__name__)
app.config['JSON_SORT_KEYS'] = False

@app.route("/")
def index():
    return "Welcome To UK Accidents Prediction Matching Learning Modal"
