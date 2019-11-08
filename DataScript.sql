INSERT INTO Usr VALUES (0,'Toledo','Jose','Pena',617085489,'Jo','pass'); 

INSERT INTO Usr VALUES (1,'Caceres','Enrique','Vilchez',678143567,'Enri','pass');

INSERT INTO Usr VALUES (2,'Madrid','Alvaro','Espinosa',678254342,'Alv','pass');

INSERT INTO IoTDevice VALUES (1, 'https://tmp.com', 'temp12', 3, 'temp sensor until 30 degrees', 0, 1);

INSERT INTO IoTDevice VALUES (2, 'https://hum.com', 'humidA', 2, 'This sensor measures the humidicity in Ulriken', 0, 1);

INSERT INTO IoTDevice VALUES (3, 'https://light.com', 'lightX1', 1, 'This sensor measures the light level in the university of HVL', 0, 2);

INSERT INTO IoTDevice VALUES (4, 'https://humB.com', 'humidB', 5, 'This sensor measures the humidicity in Ulriken', 1, 1);

INSERT INTO IoTDevice VALUES (5, 'https://rainfall.com', 'rainfallX_c', 4, 'This sensor measures the rainfall in Ulriken', 1, 2);

INSERT INTO State VALUES (0, 'TURNED OFF');

INSERT INTO State VALUES (1, 'TURNED ON');

INSERT INTO Category VALUES (0, 'This category belong to temperature sensor','TEMP');

INSERT INTO Category VALUES (1, 'This category belong to humidicity sensor','HUM');

INSERT INTO Category VALUES (2, 'This category belong to light sensor','LIGHT');

INSERT INTO Category VALUES (3, 'This category belong to rainfall sensor','RAINF');

INSERT INTO ACCESS_IOTDEV_USER VALUES (0, 1);

INSERT INTO ACCESS_IOTDEV_USER VALUES (1, 1);

INSERT INTO ACCESS_IOTDEV_USER VALUES (1, 2);

INSERT INTO ACCESS_IOTDEV_USER VALUES (3, 1);

INSERT INTO ACCESS_IOTDEV_USER VALUES (3, 2);

INSERT INTO ACCESS_IOTDEV_USER VALUES (3, 3);

INSERT INTO FEEDBACK VALUES (1, 'This is a good IoT Device for measure the temperature', 4, 1);
INSERT INTO FEEDBACK VALUES (2, 'I am glad that with this device I can see the temperature', 3, 1);
INSERT INTO FEEDBACK VALUES (3, 'Well Done!', 5, 1);

INSERT INTO FEEDBACK VALUES (4, 'This is a good IoT Device for measure the temperature', 2, 2);
INSERT INTO FEEDBACK VALUES (5, 'I am glad that with this device I can see the temperature', 3, 2);
INSERT INTO FEEDBACK VALUES (6, 'Good Job!', 1, 2);

INSERT INTO FEEDBACK VALUES (7, 'This is a good IoT Device for measure the temperature', 4, 3);
INSERT INTO FEEDBACK VALUES (8, 'Well Done!', 3, 3);

INSERT INTO FEEDBACK VALUES (9, 'Good Job!', 5, 4);

INSERT INTO FEEDBACK VALUES (10, 'Well Done!', 5, 5);

INSERT INTO JOIN_IOTDEV_CAT VALUES (0,1);
INSERT INTO JOIN_IOTDEV_CAT VALUES (1,2);
INSERT INTO JOIN_IOTDEV_CAT VALUES (2,3);
INSERT INTO JOIN_IOTDEV_CAT VALUES (1,4);
INSERT INTO JOIN_IOTDEV_CAT VALUES (3,5);
