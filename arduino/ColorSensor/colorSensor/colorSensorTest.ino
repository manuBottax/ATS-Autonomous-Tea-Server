const int s0 = 8;  
const int s1 = 9;  
const int s2 = 12;  
const int s3 = 11;  
const int out = 10;   

// values
int red = 0;  
int green = 0;  
int blue = 0;  

void setup()   
{  
  Serial.begin(9600); 
 
  pinMode(s0, OUTPUT);  
  pinMode(s1, OUTPUT);  
  pinMode(s2, OUTPUT);  
  pinMode(s3, OUTPUT);  
  pinMode(out, INPUT);   
  
  digitalWrite(s0, LOW);  
  digitalWrite(s1, HIGH);  
} 
 
void loop() 
{  
  color();
  
  if (red < blue && red < green)
  {  
    if (red <=2000 && green <=2000 && blue <=2000){ 
      Serial.println("WHITE");   
    } else{
      Serial.println("RED"); 
    }
  
  }  
  
  else if (blue < red && blue < green)   
  {  
    if (red <=2000 && green <=2000 && blue <=2000){ 
      Serial.println("WHITE");   
    } else{
      Serial.println("BLUE"); 
    }
  }
  
  else if (green < red && green < blue)  
  {  
    if (red <=2000 && green <=2000 && blue <=2000){
      Serial.println("WHITE");   
    } else{
      Serial.println("GREEN");  
    } 
  }  
    
  else{
   Serial.println("BOH");  
  }

  delay(3000);
}

//read RGB values from sensor
void color()  
{    
  digitalWrite(s2, LOW);  
  digitalWrite(s3, LOW);  
  red = pulseIn(out, digitalRead(out) == HIGH ? LOW : HIGH);  
  digitalWrite(s3, HIGH);  
  blue = pulseIn(out, digitalRead(out) == HIGH ? LOW : HIGH);  
  digitalWrite(s2, HIGH);  
  green = pulseIn(out, digitalRead(out) == HIGH ? LOW : HIGH);  
}
