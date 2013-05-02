# -*- coding: utf-8 -*-
#
# Copyright (C) 2013 GSyC/LibreSoft, Universidad Rey Juan Carlos
#
# This program is free software; you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation; either version 2 of the License, or
# (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program; if not, write to the Free Software
# Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
#
# Author: Athanasios-Ilias Rousinopoulos <athanrous@gmail.com>
# 
#

#!/bin/bash

python db_cvsanaly.py
gits=`cat repositories`
mkdir repos
cd repos
for clone in $gits
  do
  git clone $clone
  done

dirs=`ls`
echo $dirs
for dir in $dirs 
  
  do  
  cvsanaly2 --db-user root --db-password root --db-database db_01 $dir
  done
