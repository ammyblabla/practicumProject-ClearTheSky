#define x 0
#define y 1
#define z 2

void setup()
{
  pinMode(PIN_PC3, INPUT);
  pinMode(PIN_PC4, INPUT);
  pinMode(PIN_PD3, OUTPUT);
  Serial.begin(9600);
}
void loop()
{
  Serial.print("accelero: ");
  Serial.println(calAccel(accel(),z));
//  printf("piezo: ");
//  Serial.println(piezo());
}
int accel() {
   return analogRead(PIN_PC3);
}
float piezo() {
  int piezoADC = analogRead(PIN_PC4);
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
  digitalWrite(PIN_PD3, LOW);
  delay(250);
  Serial.print("eieie");
  digitalWrite(PIN_PD3, HIGH);
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




