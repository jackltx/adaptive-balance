wrk -t4 -c1024 -d60s -T5 --script=./internal-service/wrk.lua --latency http://localhost:8087/invoke

