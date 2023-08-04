curl -X POST https://results.sporttiming.by/api/results/individual/get -H "Content-Type: application/json;charset=UTF-8" -d '{"eventId":"8b54b2a0-17a0-461e-b1e9-d6dd3fd26c0d","raceId":"561cee88-aa6f-48bb-b8a8-07cd1dee5df1","page":{"skip":0,"take":100},"filter":{},"isStagesOn":true,"language":"ru"}' | jq -c -r '.results[] | [.position, .fullName, .age, .city, .raceClub, .stageResults[0].stageTime, .stageResults[1].stageTime, .stageResults[2].stageResults[0].stageTime, .stageResults[2].stageResults[0].stageTime, .stageResults[2].stageResults[0].stageTime, .stageResults[2].stageResults[0].stageTime, .stageResults[2].stageResults[0].stageTime, .stageResults[2].stageResults[1].stageTime, .stageResults[2].stageResults[2].stageTime, .stageResults[2].stageResults[3].stageTime, .stageResults[2].stageResults[4].stageTime, .stageResults[2].stageResults[5].stageTime, .stageResults[2].stageResults[6].stageTime, .stageResults[2].stageResults[7].stageTime, .stageResults[2].stageResults[8].stageTime, .stageResults[2].stageResults[9].stageTime, .stageResults[2].stageResults[10].stageTime, .stageResults[2].stageResults[11].stageTime, .stageResults[2].stageResults[12].stageTime, .stageResults[2].stageResults[13].stageTime, .stageResults[2].stageResults[14].stageTime, .stageResults[3].stageTime, .stageResults[4].stageResults[0].stageTime, .stageResults[4].stageResults[1].stageTime, .stageResults[4].stageResults[2].stageTime, .stageResults[4].stageResults[3].stageTime, .stageResults[4].stageResults[4].stageTime, .stageResults[4].stageResults[5].stageTime, .stageResults[4].stageResults[6].stageTime, .stageResults[4].stageResults[7].stageTime, .stageResults[4].stageResults[8].stageTime, .stageResults[4].stageResults[9].stageTime, .stageResults[4].stageResults[10].stageTime, .individualResult] | @csv' > ~/Projects/results-analyzer/Minsk_Triathlon_2023/sporttiming_raw.csv
curl -X POST https://results.sporttiming.by/api/results/individual/get -H "Content-Type: application/json;charset=UTF-8" -d '{"eventId":"8b54b2a0-17a0-461e-b1e9-d6dd3fd26c0d","raceId":"561cee88-aa6f-48bb-b8a8-07cd1dee5df1","page":{"skip":100,"take":100},"filter":{},"isStagesOn":true,"language":"ru"}' | jq -c -r '.results[] | [.position, .fullName, .age, .city, .raceClub, .stageResults[0].stageTime, .stageResults[1].stageTime, .stageResults[2].stageResults[0].stageTime, .stageResults[2].stageResults[0].stageTime, .stageResults[2].stageResults[0].stageTime, .stageResults[2].stageResults[0].stageTime, .stageResults[2].stageResults[0].stageTime, .stageResults[2].stageResults[1].stageTime, .stageResults[2].stageResults[2].stageTime, .stageResults[2].stageResults[3].stageTime, .stageResults[2].stageResults[4].stageTime, .stageResults[2].stageResults[5].stageTime, .stageResults[2].stageResults[6].stageTime, .stageResults[2].stageResults[7].stageTime, .stageResults[2].stageResults[8].stageTime, .stageResults[2].stageResults[9].stageTime, .stageResults[2].stageResults[10].stageTime, .stageResults[2].stageResults[11].stageTime, .stageResults[2].stageResults[12].stageTime, .stageResults[2].stageResults[13].stageTime, .stageResults[2].stageResults[14].stageTime, .stageResults[3].stageTime, .stageResults[4].stageResults[0].stageTime, .stageResults[4].stageResults[1].stageTime, .stageResults[4].stageResults[2].stageTime, .stageResults[4].stageResults[3].stageTime, .stageResults[4].stageResults[4].stageTime, .stageResults[4].stageResults[5].stageTime, .stageResults[4].stageResults[6].stageTime, .stageResults[4].stageResults[7].stageTime, .stageResults[4].stageResults[8].stageTime, .stageResults[4].stageResults[9].stageTime, .stageResults[4].stageResults[10].stageTime, .individualResult] | @csv' >> ~/Projects/results-analyzer/Minsk_Triathlon_2023/sporttiming_raw.csv
curl -X POST https://results.sporttiming.by/api/results/individual/get -H "Content-Type: application/json;charset=UTF-8" -d '{"eventId":"8b54b2a0-17a0-461e-b1e9-d6dd3fd26c0d","raceId":"561cee88-aa6f-48bb-b8a8-07cd1dee5df1","page":{"skip":200,"take":100},"filter":{},"isStagesOn":true,"language":"ru"}' | jq -c -r '.results[] | [.position, .fullName, .age, .city, .raceClub, .stageResults[0].stageTime, .stageResults[1].stageTime, .stageResults[2].stageResults[0].stageTime, .stageResults[2].stageResults[0].stageTime, .stageResults[2].stageResults[0].stageTime, .stageResults[2].stageResults[0].stageTime, .stageResults[2].stageResults[0].stageTime, .stageResults[2].stageResults[1].stageTime, .stageResults[2].stageResults[2].stageTime, .stageResults[2].stageResults[3].stageTime, .stageResults[2].stageResults[4].stageTime, .stageResults[2].stageResults[5].stageTime, .stageResults[2].stageResults[6].stageTime, .stageResults[2].stageResults[7].stageTime, .stageResults[2].stageResults[8].stageTime, .stageResults[2].stageResults[9].stageTime, .stageResults[2].stageResults[10].stageTime, .stageResults[2].stageResults[11].stageTime, .stageResults[2].stageResults[12].stageTime, .stageResults[2].stageResults[13].stageTime, .stageResults[2].stageResults[14].stageTime, .stageResults[3].stageTime, .stageResults[4].stageResults[0].stageTime, .stageResults[4].stageResults[1].stageTime, .stageResults[4].stageResults[2].stageTime, .stageResults[4].stageResults[3].stageTime, .stageResults[4].stageResults[4].stageTime, .stageResults[4].stageResults[5].stageTime, .stageResults[4].stageResults[6].stageTime, .stageResults[4].stageResults[7].stageTime, .stageResults[4].stageResults[8].stageTime, .stageResults[4].stageResults[9].stageTime, .stageResults[4].stageResults[10].stageTime, .individualResult] | @csv' >> ~/Projects/results-analyzer/Minsk_Triathlon_2023/sporttiming_raw.csv