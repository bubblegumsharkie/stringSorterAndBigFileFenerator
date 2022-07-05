# Тестовое задание на позицию Java Junior developer
#### [Постановка задачи](https://github.com/tomcat77/junior-java-developer-test-task/blob/main/README.md)

## Выполнение
Для описания генератора файла со строками нужно было принять стандартную строку. В исходных данных были строки в нижнем регистре, состоящие из букв a-z и цифр.

Если файл больше, чем допустим, производится дробление, с группировкой по первым символам, по умолчанию:
```
amountOfCharsToGroupBy = 2
```

Для генерации файла по умолчанию используются следующие параметры:
```
IS_GENERATED = true
maxGeneratedLineLength = 300
amountOfGeneratedLines = 10000
maximumAmountOfSupportedLinesInFile = 5000
```
Меняя первую опцию, мы можем отключить генерацию файла и воспользоваться файлом, полученным иным способом.
Если же мы хотим генерировать, то можно поменять соотвественно: длину генерируемой строки, количество генерируемых строк и количество допустимых строк для сортировщика.

По умолчанию, файлы хранятся в:
```sh
"./src/files/"
```

где файл с названием generated.txt является входными данными, а файл с названием result.txt является результатом сортировки.

Названия файлов так же можно быстро изменить, поменяв значения строк:
```sh
GENERATED_FILE_NAME = "generated.txt"
SORTED_FILE_NAME = "result.txt"
```
