from PyPDF2 import PdfFileReader, PdfFileWriter
import os

from IndiJobs.settings import BASE_DIR

#from django.conf import settings

def getPdfdata(user_id):
    pdfPath = "location/of/pdf/filename.pdf"
    pdf = PdfFileReader(pdfPath)
    pdf_contents = pdf.getPage(0).extractText().lower().split("\n")

    return pdf_contents
    


