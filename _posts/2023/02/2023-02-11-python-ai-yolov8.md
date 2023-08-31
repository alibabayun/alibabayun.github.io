---
layout: post
title: "使用YOLOv8做目标检测"
keywords: ["python"]
description: "使用YOLOv8做目标检测"
category: "python"
tags: ["python"]
---
## 前言
YOLOv8是来自Ultralytics的最新的基于YOLO的对象检测模型系列，提供最先进的性能。
利用以前的 YOLO 版本，YOLOv8模型更快、更准确，同时为训练模型提供统一框架，以执行

## 模型训练
到这里下载模型素材：https://universe.roboflow.com/search，下载后把以下方法放到目录中
```python
from ultralytics import YOLO

# Load a model
model = YOLO("yolov8n.yaml")  # build a new model from scratch
model = YOLO("yolov8n.pt")  # load a pretrained model (recommended for training)

# Use the model
model.train(data="data.yaml", epochs=5)  # train the model
metrics = model.val()  # evaluate model performance on the validation set
results = model("test.jpeg")  # predict on an image
success = model.export(format="onnx")  # export the model to ONNX format
```

## 模型使用
把训练好的模型`weights/`目录，放到使用的目录。如以下代码
```
model = YOLO("weights/best.pt")
results = model.predict(0)
```

## 参考资料：
https://blog.csdn.net/stq054188/article/details/128925932