import React from "react";
export default function LoadLocalStorage(){
      let itemList = [
        {
        itemID : '1',
        itemName : "item1",
        itemThumbnail : "https://img.icons8.com/external-victoruler-linear-colour-victoruler/64/000000/external-product-logistics-victoruler-linear-colour-victoruler.png",
        itemRating : 3,
        itemPrice : 200,
        },
        {
        itemID : '2',
        itemName : "item2",
        itemThumbnail : "https://img.icons8.com/external-photo3ideastudio-lineal-color-photo3ideastudio/64/000000/external-shirt-travel-checklist-photo3ideastudio-lineal-color-photo3ideastudio.png",
        itemRating : 4.5,
        itemPrice : 300,
        },
        {
        itemID : '3',
        itemName : "item3",
        itemThumbnail : "https://img.icons8.com/external-kiranshastry-lineal-color-kiranshastry/64/000000/external-laptop-cyber-security-kiranshastry-lineal-color-kiranshastry-2.png",
        itemRating : 4,
        itemPrice : 100,
        },
        {
        itemID : '4',
        itemName : "item4",
        itemThumbnail : "https://img.icons8.com/external-icongeek26-linear-colour-icongeek26/64/000000/external-phone-essentials-icongeek26-linear-colour-icongeek26.png",
        itemRating : 3,
        itemPrice : 200,
        }
    ]
    localStorage.setItem("Items_List", JSON.stringify(itemList));
}