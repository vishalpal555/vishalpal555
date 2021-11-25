import requests
import json
import csv
import pandas
import os

csv_file = "location/of/csvfile/location_by_ip.csv"

def getLocation(request_ip):
    location = dict()
    ip_df = pandas.read_csv(csv_file)

    if request_ip in list(ip_df["ip"]):
        location["ip"] = ip_df[ip_df["ip"] == request_ip].ip.item()
        location["city"] = ip_df[ip_df["ip"] == request_ip].city.item()
        location["state"] = ip_df[ip_df["ip"] == request_ip].state.item()

    else:
        myApiKey = "your api key"
        response = requests.get(f"https://api.ipregistry.co/{request_ip}?key={myApiKey}")
           "state" : json_dic["location"]["region"]["name"]}

        location["ip"] = request_ip
        if "location" in json_dic.keys():
            if "city" in json_dic["location"].keys():
                location["city"] = json_dic["location"]["city"]
            if "region" in json_dic["location"].keys():
                if "name" in json_dic["location"]["region"].keys():
                    location["state"] = json_dic["location"]["region"]["name"]
        
        ip_df = pandas.DataFrame([location])
        ip_df.to_csv(csv_file, index=False, mode="a", header=False)
    

    return location


