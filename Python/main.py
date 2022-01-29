from PIL import Image
import numpy

for i in range(68,141):
    img = Image.open(f"with_mask/0 ({i}).jpg")
    # img = img.rotate(-90)
    img = img.resize((350,450), Image.ANTIALIAS)
    img.save(f"with_mask_resized/0 ({i}).jpg")