gits=`cat mailing_lists`
for ml in $gits
  do
  mlstats --db-user root --db-password root --db-name mlstats_tesis --db-admin-user root --db-admin-password root $ml
  done
