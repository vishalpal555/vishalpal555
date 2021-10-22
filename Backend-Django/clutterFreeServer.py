#new file will be updated and old will be removed
#you can give new_name as instance.pk
#KEEP UR SERVER CLEAN
#deletes redundent files

import re
import os
from django.db import models

def fileRename(instance, filename):
        upload_to = 'file/path'
        ext = filename.split(".")[-1]
        new_name = "new_name"
        for file in os.listdir(f'{upload_to}'):
                if (re.match(f"{instance.pk}\.[a-z]", file)):
                        os.remove(f"{new_name}/{file}")
                
        filename = new_name + "." + ext
        return os.path.join(upload_to,filename)
      
class FileUploadTable(models.Model):
        file_upload = models.FileField(blank=True, null=True, upload_to=filename)
  
