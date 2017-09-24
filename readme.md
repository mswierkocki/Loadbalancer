# LoadBalancer
Simple REST application that route traffic to different backends depending on a group that user falls into.

## Usage
Application binds to ``localhost`` on port ``8888``

To run use:

	 ./gradlew run

 
Configuration is stored in file config.properties.
E.g.:

  ```
A = 30
B = 20
C = 50
  ```
  Example API input:
  ``` curl http://localhost:8888/route?id={id} ```
##Benchmark
Testing platform: Intel i5-2550K 3.4GHz x 4


 ``ab -n1000000 -c10 http://localhost:8888/route?id= ``
 
 Results:


  ```
 
Server Software:        
Server Hostname:        localhost
Server Port:            8888

Document Path:          /route?id=
Document Length:        1 bytes

Concurrency Level:      10
Time taken for tests:   63.209 seconds
Complete requests:      1000000
Failed requests:        0
Total transferred:      135000000 bytes
HTML transferred:       1000000 bytes
Requests per second:    15820.53 [#/sec] (mean)
Time per request:       0.632 [ms] (mean)
Time per request:       0.063 [ms] (mean, across all concurrent requests)
Transfer rate:          2085.71 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    0   0.1      0       6
Processing:     0    1   0.3      1      13
Waiting:        0    1   0.3      1      13
Total:          0    1   0.3      1      13

Percentage of the requests served within a certain time (ms)
  50%      1
  66%      1
  75%      1
  80%      1
  90%      1
  95%      1
  98%      1
  99%      1
 100%     13 (longest request)

  ```


	 