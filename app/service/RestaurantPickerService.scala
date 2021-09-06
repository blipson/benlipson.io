package service
import service.RestaurantPickerService.RESTAURANT_LIST

import javax.inject.Singleton
import scala.util.Random

@Singleton
class RestaurantPickerService {
  def pickRestaurant(): Option[String] = {
    if (RESTAURANT_LIST.isEmpty) None
    else RESTAURANT_LIST.lift(Random.nextInt(RESTAURANT_LIST.length))
  }
}

object RestaurantPickerService {
  private val asianFusion = Seq(
    "Wok in the Park",
    "Dumpling",
    "Little Mekong",
    "Sweet Basil"
  )
  private val chinese = Seq(
    "Lao Sze Chuan",
    "Tea House",
    "Jun",
    "Little Szechuan",
    "Szechuan Spice",
    "Rainbow Chinese Restaurant and Bar",
    "Yangtze",
    "Peking Garden",
    "Grand Szechuan"
  )
  private val korean = Seq(
    "Pizzaria Lola",
    "Young Joni",
    "Kimchi Tofu House",
    "Sole Cafe"
  )
  private val japanese = Seq(
    "Wakame Sushi & Asian Bistro",
    "Kyatchi"
  )
  private val thai = Seq(
    "On's Kitchen",
    "Sen Yai Sen Lek",
    "Lemongrass Thai Cuisine",
    "Thai Table",
    "Ruam Mit Thai + Lao Food"
  )
  private val nepalese = Seq(
    "Everest on Grand",
    "Himalayan"
  )
  private val mexican = Seq(
    "Andale Taqueria",
    "El Asador",
    "Dominguez Restaurant",
    "La Loma Tamales"
  )
  private val indian = Seq(
    "Darbar",
    "Curry in a Hurry",
    "Namaste Café",
    "Best of India Indian Restaurant",
    "India Cafe"
  )
  private val italian = Seq(
    "Bar la Grassa",
    "Mucci's Italian",
    "Pig Ate My Pizza Kitchen + Brewery",
    "Cossetta",
    "Broders' Cucina Italiana"
  )
  private val middleEastern = Seq(
    "Holy Land",
    "Caspian Bistro and Gourmet Marketplace",
    "Marhaba Grill",
    "Mediterranean Cruise Cafe"
  )
  private val persian = Seq(
    "Caspian Bistro and Gourmet Marketplace"
  )
  private val comfortAndSoul = Seq(
    "Milton's",
    "Good Day Cafe",
    "Mojo Monkey Donuts",
    "Hello Pizza",
    "Red Wagon Pizza Company",
    "Maria's Cafe",
    "Black Coffee and Waffle Bar"
  )
  private val latin = Seq(
    "Senora Grill",
    "Cafe Racer",
    "Hola Arepa",
    "Ena",
    "Cocina Latina"
  )
  private val bakery = Seq(
    "Isles Bun & Coffee",
    "Mel-O-Glaze Bakery",
    "Sara Jane's Bakery",
    "Butter Bakery Cafe"
  )
  private val diner = Seq(
    "Hot Plate",
    "The Tiny Diner",
    "Nighthawks Diner & bar",
    "The Hi-Lo Diner",
    "Uptown Diner",
    "The Nicollet Diner"
  )
  private val burgers = Seq(
    "Saint Dinette",
    "Parlour",
    "Revival",
    "Lyn65",
    "Lowry Hill Meats",
    "Matt's Bar",
    "Bebe Zito",
    "Blue Door Pub",
    "Petite León",
    "Animales",
    "112 Eatery",
    "Stewart's",
    "Chip's Clubhouse"
  )

  val RESTAURANT_LIST: Seq[String] =
    asianFusion ++
      chinese ++
      korean ++
      japanese ++
      thai ++
      nepalese ++
      mexican ++
      indian ++
      italian ++
      middleEastern ++
      persian ++
      comfortAndSoul ++
      latin ++
      bakery ++
      diner ++
      burgers.distinct
}
