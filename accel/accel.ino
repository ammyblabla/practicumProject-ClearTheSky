#define x 0
#define y 1
#define z 2
#define greenlight PIN_PD3
#define accelPIN PIN_PC3
#define piezoPIN PIN_PC4

void setup()
{
  pinMode(accelPIN, INPUT);
  pinMode(piezoPIN, INPUT);
  pinMode(greenlight, OUTPUT);
  Serial.begin(9600);
}
void loop()
{
  //format accelZ piezo
//  Serial.print("accelero: ");
  Serial.print(calAccel(accelRead(),z));
//  printf("piezo: ");
//  Serial.println(piezo());
  Serial.println(",0");
}
int accelRead() {
   return analogRead(accelPIN);
}
float piezoRead() {
  int piezoADC = analogRead(piezoPIN);
  return piezoADC * 5 / 1023.0 ;
}
float findmax(float value) {
  static float max = 0.0;
  static int isPreviousZero = 0;

  if(value > max){
    max = value;
    isPreviousZero = 1;
  }
  if(value == 0 && isPreviousZero == 1 && max != 0)
  {
    isPreviousZero = 0;
    float tmp = max;
    max = 0;
    return tmp;
  }
  return 0.0;
}

void testboard()
{
  digitalWrite(greenlight, LOW);
  delay(250);
  Serial.print("eieie");
  digitalWrite(greenlight, HIGH);
  delay(250);
}

float calAccel(int value, int axis)
{
//  Serial.println(value);
  if (axis == x)
    return ((float)value - 331.5)/65*9.8;  //print x value on serial monitor
  if (axis == y)
    return ((float)value - 329.5)/68.5*9.8;  //print y value on serial monitor
  if (axis == z)
    return ((float)value - 340)/68*9.8;  //print z value on serial monitor
  return 0;
}




