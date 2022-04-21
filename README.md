# DataEngineer_ScalaPractice
It is a Scala application that:
- Connects to the DB2 instance on the cloud
- Generates a table with specified number of sample records
   - product_id - autogenerated numeric
   - product_group - autogenerated numeric in 0..9 range
   - year - autogenerated numeric in 2015..2018 range
   - 12 columns with monthly purchases amount - numeric in 0..100000 range
   - There should be no duplicates for the same product/year
   - If there are multiple product rows for different years, product/group combination should be concise

```sh
+----------+-------------+----+-------+-------+-------+-------+-------+-------+-------+-------+-------+--------+--------+--------+
|PRODUCT_ID|PRODUCT_GROUP|YEAR|MONTH_1|MONTH_2|MONTH_3|MONTH_4|MONTH_5|MONTH_6|MONTH_7|MONTH_8|MONTH_9|MONTH_10|MONTH_11|MONTH_12|
+----------+-------------+----+-------+-------+-------+-------+-------+-------+-------+-------+-------+--------+--------+--------+
|         1|            3|2015|  14615|  21523|  80036|  90914|  60741|  89532|  50597|  49857|   8635|    8263|   41150|   72746|
|         2|            9|2015|  96048|  19381|  94104|  69315|  79990|   9393|  78665|  15577|  47372|   21797|   87715|   39186|
|         3|            1|2015|  37003|  19598|  33088|  73432|  43261|  48288|  26077|  15311|  10620|   35989|   16799|   31384|
|         4|            2|2015|  22352|  15045|  77233|  15544|  11868|  54509|  36917|  50491|  87679|    6861|   52090|   39246|
|         5|            8|2015|  52451|  93217|  76382|    658|  27107|  38078|  82580|  75636|  28327|   90511|   43596|   17962|
|         6|            2|2015|  96558|  79860|  26931|  47509|  70879|  55372|  50849|  70883|   2287|   14231|   66020|    5372|
|         7|            9|2015|   1494|  43652|  82459|  17525|  64236|  44902|  66207|  78152|   6779|   34984|   19754|   10574|
|         8|            4|2015|  57732|  10365|  97888|  95551|   7012|   5979|  13232|  41639|  19728|   52076|   71080|   89554|
|         9|            1|2015|  56760|  19502|  44565|   1768|  37781|  96102|  44964|  63942|  69791|   48281|    6677|   24254|
|        10|            5|2015|  41834|  46442|   6846|  63346|  73471|  60200|  67050|  75314|  83853|   33794|   38438|   47948|
|        11|            0|2015|  12391|  29764|  65134|  17768|  56131|  83640|  84867|  22921|  27903|   18110|   89642|   92019|
|        12|            8|2015|  86389|  53870|  84657|  33009|  61348|  26468|  53647|  50761|  60047|   94610|   24584|   29078|
|        13|            4|2015|  54821|  73645|  95937|  17547|  85056|  45450|  34630|  81893|  76028|   71022|   82996|   17145|
|        14|            9|2015|  80914|  85303|  91165|  79562|  50558|   7090|  20629|  67044|  16113|   44507|   16469|   71655|
|        15|            1|2015|  74509|   2960|  29736|  60888|  66250|  77978|   2266|  35715|   8549|    2891|   58800|   73462|
|        16|            3|2015|  39715|  84183|  28871|  85442|  63529|  40100|  58296|  51041|   8777|   21380|   46982|   61122|
|        17|            2|2015|  61957|  77239|  94460|  66772|  78252|  89642|  70356|   2929|  57172|   84640|   65759|   57011|
|        18|            0|2015|  68885|  93133|   6258|  49745|  99673|  56558|  65906|  85954|  60925|   72363|   29782|   34897|
|        19|            9|2015|   5103|  59785|  55332|  33792|  42955|   8219|  89019|  76573|  64752|   19409|   34708|   11897|
|        20|            6|2015|   3755|  90636|  64631|   6879|  67547|   8223|  85576|  29056|  41152|   89151|   35371|   91746|
+----------+-------------+----+-------+-------+-------+-------+-------+-------+-------+-------+-------+--------+--------+--------+
only showing top 20 rows
```
 
## Requirements
Strongly recomended to use:

| Name | Version |
| ------ | ------ |
| SBT | 1.6.2 |
| JDK | 11 and higher |
| Scala | 2.13.8 |
| Git | 2.33.0 |

## Build
>All next steps you can do from <b>cmd</b> or <b>PowerShell</b>.
Clone the current version of the repository to your computer.
```sh
 git clone https://github.com/YuliyaValova/DataEngineer_ScalaPractice
```

### Using sbt
Go to the project folder and write this commands to start sbt, reload and package project into jar.
```sh
sbt 
```
```sh
reload
```
```sh
package
```

## Run
### Using sbt
Use this command in sbt-shell, opened in your project folder.
```sh
run jdbc:db2://<URL>/<DATABASE_NAME> <USER_NAME> <YOUR_PASSWORD> <TABLE_NAME> <NUMBER_OF_GENERATED_ROWS>
```
- URL - Url for connection to DB2 on Cloud. <br>
- DATABASE_NAME - Name of the database. <br>
- USER_NAME - Username from DB2 credentials. <br>
- YOUR_PASSWORD - Password from DB2 credentials. <br>
- TABLE_NAME - The name under which the generated table will be saved in the database. <br>
- NUMBER_OF_GENERATED_ROWS (Optional, by default 20000) - Number of rows generated for the table.
<br>

<b> Example </b>
<br>
Command with your custom number of genereted rows:
```sh
sbt run jdbc:db2://b1bc1839-6f45-4pd4-bef4-10cf081900bf.c1ogj3dd0tgtu0lade00.databases.appdomain.cloud:34305/bludb qlf38947 CG745Jpa7a930Jvb Test 15000
```
<b> or </b>
<br>
Command with default number of genereted rows:
```sh
sbt run jdbc:db2://b1bc1839-6f45-4pd4-bef4-10cf081900bf.c1ogj3dd0tgtu0lade00.databases.appdomain.cloud:34305/bludb qlf38947 CG745Jpa7a930Jvb Test 
```
 

