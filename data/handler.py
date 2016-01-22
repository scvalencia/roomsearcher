import os
import subprocess
from shutil import copyfile

SCRIPT = 'prettify_json.rb'

original_path = os.getcwd()
all_subdirs = [d for d in os.listdir('.') if os.path.isdir(d)]
latest_subdir = max(all_subdirs, key=os.path.getmtime)
os.chdir(original_path + '/' + latest_subdir)

copyfile(original_path + '/' + SCRIPT, os.getcwd() + '/' + SCRIPT)

counter = 0
for f in os.listdir('.'):

	if f != 'temp.json' and f != SCRIPT:	
		os.popen('ruby prettify_json.rb %s > tmp.json' % f)
		os.popen('cp tmp.json %s' % f)
		os.popen('rm tmp.json')

	if counter % 8 == 0:
		os.popen('git add %s' % f)
		os.popen('git commit -m "New courses data for processing"')

	counter += 1

os.remove(SCRIPT)