#define x 0
#define y 1
#define z 2
#define greenlight PIN_PD3
#define ypin PIN_PC3
#define zpin PIN_PC5
#define xpin PIN_PC1
#define piezoPIN PIN_PC4
  
static float max = 0.0;
static int isPreviousZero = 0;

void setup()
{
  pinMode(zpin, INPUT);
  pinMode(piezoPIN, INPUT);
  pinMode(greenlight, OUTPUT);
  Serial.begin(9600);
}
void loop()
{
//  testboard();
  accelMain();
  Serial.print(",");
  piezoMain();

}

void accelMain() {
//  Serial.print(accelRead());
//  Serial.print(",");
    int ans = 0;
    ans  = constrain(map(accelRead(),280,350,0,90),0,90);
  Serial.print(ans);
  //Serial.print(constrain(map(accelRead(),0,90,0,90),0,90));
//  Serial.print(",");
//  Serial.println(calAccel(accelRead(),z));
}

void piezoMain() {
  //  if(findmax(piezoRead()) == 0.00f){
//    Serial.print("raw: ");
//    Serial.println(piezoRead());
//    Serial.print("max ");
    float maxx = 0;
    maxx = findmax(piezoRead());
    Serial.println(maxx);

//  }
//  Serial.println(",0");
}

int accelRead() {
   return analogRead(zpin);
}
float piezoRead() {
  int piezoADC = analogRead(piezoPIN);
  return piezoADC * 5 / 1023.0 ;
}
float findmax(float value) {
//  Serial.print("findmax: ");
//  Serial.println(max);

  if(value > max){
    max = value;
    isPreviousZero = 1;
  }
  if(value == 0 && isPreviousZero == 1 && max != 0)
  {
    isPreviousZero = 0;
    float tmp = max;
    max = 0.0;
    return tmp;
  }
  return 0.0;
}

void testboard()
{
  digitalWrite(greenlight, LOW);
  delay(100);
  Serial.print("eieie");
  digitalWrite(greenlight, HIGH);
  delay(100);
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




