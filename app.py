from Flask import Flask,render_template
import joblib
from Flask import request
from Flask import jsonify
import numpy as np
import os



app = Flask(__name__)
app.config['JSON_SORT_KEYS'] = False

#example query: http://localhost:8080/predict?month=1&lon=-0.079786&lat=51.517576

@app.route('/predict', methods=['GET'])
def predict():
    dir_path = os.path.dirname(os.path.realpath(__file__))
    try:
        clf = joblib.load("{}/ml-model/model.pkl".format(dir_path))
    except:
        return jsonify({'error': 'Failed to load model'})



    month = request.args.get('Date')
    lon = request.args.get('lon')
    lat = request.args.get('lat')

    prediction = clf.predict([[month,lon,lat]]) #prediction of model
    probs = clf.predict_proba([[month,lon,lat]]).tolist() #probability of predictions
    classes = clf.classes_.tolist() # all classes

    data = [
        {'probability': probs[0][0], 'class': classes[0]},
        {'probability': probs[0][1], 'class': classes[1]},
        {'probability': probs[0][2], 'class': classes[2]},
        {'probability': probs[0][3], 'class': classes[3]},
        {'probability': probs[0][4], 'class': classes[4]},
        {'probability': probs[0][5], 'class': classes[5]},
        {'probability': probs[0][6], 'class': classes[6]},
        {'probability': probs[0][7], 'class': classes[7]},
        {'probability': probs[0][8], 'class': classes[8]},
        {'probability': probs[0][9], 'class': classes[9]},
        {'probability': probs[0][10], 'class': classes[10]},
        {'probability': probs[0][11], 'class': classes[11]},
       
    ]


    return jsonify({'prediction': str(prediction[0]),"data":data})
   
@app.route('/')
def intro():
    return render_template('index.html')



if __name__ == '__main__':
    app.run(threaded=True,port=8080)
