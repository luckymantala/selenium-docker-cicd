docker-compose -f grid.yaml up --scale chrome=2 -d

set BROWSER=chrome

docker-compose up

docker-compose -f grid.yaml up --scale firefox=2 -d

set BROWSER=firefox

docker-compose up

docker-compose -f grid.yaml down
docker-compose down