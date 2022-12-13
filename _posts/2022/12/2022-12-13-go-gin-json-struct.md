---
layout: post
title: "go下将json转struct"
keywords: ["go"]
description: "go下将json转struct"
category: "go"
tags: ["go"]
---

## 背景
将请求接口的数据，转到`strcut`

## 参考代码
```go

type RecordLightUpReq struct {
	Code int                 `json:"code"`
	Msg  string              `json:"msg"`
	Data []RecordLightUpData `json:"data"`
}

type RecordLightUpData struct {
	ID         int    `json:"id"`
	Code       string `json:"code"`
	Tstart     string `json:"tstart"`
	Tend       string `json:"tend"`
	Brightness int    `json:"brightness"`
	Duration   int    `json:"duration"`
	Updatedat  string `json:"updatedat"`
}

type RecordLightUp struct{}

// SyncRecord 同步数据
func (r *RecordLightUp) SyncRecord(maxId int64, maxUpDateTime string) ([]RecordLightUpData, error) {
	cfg := config.Instance()
	api := cfg.Foreign.IotEdgeUrl + "/data/v1/lampevent/sync"
	url := fmt.Sprintf("%v?id=%d&&updatedat=%v", api, maxId, url2.QueryEscape(maxUpDateTime))
	method := "GET"
	client := &http.Client{}
	req, err := http.NewRequest(method, url, nil)
	
	if err != nil {
		return nil, err
	}
	res, err := client.Do(req)
	if err != nil {
		return nil, err
	}
	defer res.Body.Close()
	body, err := ioutil.ReadAll(res.Body)
	if err != nil {
		return nil, err
	}

	result := RecordLightUpReq{}
	err = json.Unmarshal(body, &result)
	if err != nil {
		return nil, err
	}
	if result.Code != 0 {
		panic(result.Msg)
	}
	return result.Data, nil
}

```