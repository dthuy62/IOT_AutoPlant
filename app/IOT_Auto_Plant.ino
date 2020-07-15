
#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>
//
//
// Set these to run example.
#define FIREBASE_HOST "autoplant-b5120.firebaseio.com"
#define FIREBASE_AUTH "vctg4Q2avv08fCFSZHXo306fkonRlfMQaKehi8A4"
#define WIFI_SSID ""
#define WIFI_PASSWORD ""

#define WATER_PUMP 2

int sensor_pin=A0;
int soil_moisture = 0;

void setup() {
  Serial.begin(9600);

  // connect to wifi.
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("connected: ");
  Serial.println(WiFi.localIP());
  
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  pinMode(sensor_pin,INPUT);
  pinMode(WATER_PUMP,OUTPUT);
  Firebase.set("Status",0);////////////////////////
  Firebase.set("Doam",0);
}
int m = 0;
int n = 0;

void loop() {
  Serial.print("MOISTURE LEVEL : ");
   soil_moisture= analogRead(sensor_pin);
    soil_moisture = map(soil_moisture, 500, 1023, 100, 0);
   Serial.println(soil_moisture);
   Firebase.setInt("Do am dat",soil_moisture);
   delay(1000);
   n = Firebase.getInt("Status");
   m = Firebase.getInt("Do am dat");
    if (n==1  || m<20) {
    Serial.println("PUMP ON");
    digitalWrite(WATER_PUMP,HIGH);  
    return;
    }
    else  {
    Serial.println("PUMP OFF");
    digitalWrite(WATER_PUMP,LOW);  
}
}

