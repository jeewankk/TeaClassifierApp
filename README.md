# Tea Classifier Android App

This simple Android app classifies an unknown tea sample using only two useful parameters from the project results:

- TDS value
- R value from RGB color sensor

It calculates Euclidean distance from the unknown point to three reference points:

- Watawala = [TDS 311.24, R 37.58]
- B.O.P. = [TDS 152.04, R 50.53]
- O.P.1 = [TDS 97.91, R 61.16]

The closest reference point becomes the tea name.

## Formula

Distance = sqrt((UnknownTDS - ReferenceTDS)^2 + (UnknownR - ReferenceR)^2)

## How to run

1. Open Android Studio.
2. Choose **Open**.
3. Select the `TeaClassifierApp` folder.
4. Let Gradle sync.
5. Click Run.

## Main code file

`app/src/main/java/com/example/teaclassifier/MainActivity.java`
