 #!/bin/bash

cd /path to cloned repositories/
dirs=`ls`
echo $dirs
for dir in $dirs
  do
  cd $dir
  cvsanaly2 --db-user root --db-database= database_name
  cd ..
  done
