import time
from PIL import Image
import os
from concurrent.futures import ProcessPoolExecutor

imagesPath = input("Enter the folder path: ")
image_width = int(input("Enter new image width: "))
image_height = int(input("Enter new image height: "))

def resize(file):
    try:
        filePath = os.path.join(imagesPath, file)
        img = Image.open(filePath)
        #img = img.rotate(-90)  #if needs rotations
        img = img.resize((image_width, image_height), Image.ANTIALIAS)
        try:
            new_imagesPath = os.path.join(imagesPath, f"ResizedImage_{image_width}x{image_height}")
            img.save(f"{new_imagesPath}/{file}")
        except FileNotFoundError:
            print(f"Creating location: {new_imagesPath}")
            os.mkdir(new_imagesPath)
            img.save(f"{new_imagesPath}/{file}")
            print(f"Saved {file} in {new_imagesPath}")
        else:
            print(f"Saved {file} in {new_imagesPath}")
    
    except Exception as e:
        print(f"Problem resizing {file}")
        print(e)

files_list = os.listdir(imagesPath)

start_time = time.time()
with ProcessPoolExecutor() as executor:
    result = executor.map(resize, files_list)
end_time = time.time()

print(f"Time taken: {end_time-start_time} s")

