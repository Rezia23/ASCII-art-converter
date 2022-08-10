# ASCII Art

[![pipeline status](https://gitlab.fit.cvut.cz/BI-OOP/B201/asciiart/badges/master/pipeline.svg)](https://gitlab.fit.cvut.cz/BI-OOP/B201/asciiart)

The idea of this project is to load images, translate them into ASCII ART images, optionally apply filters, and save them. (https://courses.fit.cvut.cz/BI-OOP/projects/ASCII-art.html)

## How to do it

1. **Read [the instructions](https://courses.fit.cvut.cz/BI-OOP/projects/ASCII-art.html)**
2. Play [lofi hip hop radio](https://www.youtube.com/watch?v=5qap5aO4i9A)
3. [???](https://www.youtube.com/watch?v=ZXsQAXx_ao0)
4. Profit

## Supported program arguments:
* Load image
    * --image relative/or/absolute/path.jpg   //supports jpg, jpeg, png
    * --image-random                          //loads random image
* Export image
    * --output-console
    * --output-file relative/or/absolute/path
* Filter image
    * --scale X                               //supports X from (1, 0.25, 4)
    * --rotate X                              //supports X multiple of 90
    * --flip X                                //supports X from (x, y)
