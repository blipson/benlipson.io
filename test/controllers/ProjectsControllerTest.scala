package controllers

import org.scalatestplus.play.PlaySpec
import play.api.mvc.Result
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.Future

class ProjectsControllerTest extends PlaySpec {
  "Projects controller" should {
    "should display the projects page" in {
      val controller = new ProjectsController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.projects().apply(FakeRequest())
      val bodyText: String = contentAsString(result)
      bodyText.replaceAll(" +", "") mustBe
        """<html lang="en">
          |    <head>
          |        <meta charset="utf-8">
          |        <meta http-equiv="X-UA-Compatible" content="IE=edge">
          |        <meta name="viewport" content="width=device-width, initial-scale=1">
          |        <link rel="shortcut icon" href="#" />
          |        <title>Lipson</title>
          |    </head>
          |    <body>
          |        <h1>Projects</h1>
          |        <ul>
          |            <li>
          |                <a href="/projects/counterpoint">Counterpoint Generator</a>
          |                <br><i>
          |                6:00 AM - 05 Nov 2019
          |            </i>
          |                <ul>
          |                    <li>A rudimentary cantus firmus generator. It adheres to the following rules:
          |                    <ol>
          |                        <li>Length must be 8-16 notes</li>
          |                        <li>First and last note must be tonic</li>
          |                        <li>Final note must typically be approached by a step above, occasionally by a half step below</li>
          |                        <li>The leading tone always progresses to the tonic</li>
          |                        <li>All notes must be in the correct key</li>
          |                        <li>All note-to-note progressions are melodic consonances (almost, except 3rd to last -> 2nd to last)</li>
          |                        <li>No note may be followed by the same note</li>
          |                        <li>Range (interval between lowest and highest notes) of no more than a tenth</li>
          |                        <li>No more than 2 melodic motions larger a 4th or larger (leaps)</li>
          |                        <li>Any large leaps (fourth or larger) are followed by step in opposite direction</li>
          |                        <li>A single climax (high point) that appears only once in the melody</li>
          |                    </ol>
          |                </ul>
          |            </li>
          |        </ul>
          |        <ul>
          |            <li>
          |                <a href="/projects/restaurantpicker">Restaurant Picker</a>
          |                <br><i>
          |                9:00 PM - 09 Jan 2018
          |            </i>
          |                <ul>
          |                    <li>When I'm going out to eat I can never decide what restaurant to go to. I have the same problem whether I'm alone or with
          |                        friends/on a date. So I built a tool for myself that'll make the decision for me. Doesn't account for dietary restrictions.</li>
          |                </ul>
          |            </li>
          |        </ul>
          |        <ul>
          |            <li>
          |                <a href="/projects/graphtv">Graph TV</a>
          |                <br><i>
          |                9:00 PM - 09 Jan 2018
          |            </i>
          |                <ul>
          |                    <li>One of my favorite websites (KevinInformatics GraphTV) recently went down. It seems that the API key the website uses is
          |                        no longer valid. I decided to get my own API key and replace it. I find myself wanting these metrics
          |                        more often than you might think.</li>
          |                </ul>
          |            </li>
          |        </ul>
          |        <ul>
          |            <li>
          |                <a href="/projects/hammingcodes">Hamm1ng C0des</a>
          |                <br><i>
          |                9:00 PM - 26 Oct 2017
          |            </i>
          |                <ul>
          |                    <li>Have y0u ever w0ndered h0w 0perat1ng systems pr0tect aga1nst data c0rrupt1on when transferr1ng f1les t0 0r fr0m an0ther st0rage
          |                        Dev1ce, 0r when mem0ry gets cached in ram? Hamm1ng c0des ensure err0r detect10n thr0ugh the use 0f
          |                        Par1ty b1ts enc0ded w1th1n the data.</li>
          |                </ul>
          |            </li>
          |        </ul>
          |    </body>
          |</html>
          |""".stripMargin.replaceAll(" +", "")
    }

    "should display the 'Hamm1ng C0des' project page" in {
      val controller = new ProjectsController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.hammingCodes().apply(FakeRequest())
      val bodyText: String = contentAsString(result)
      bodyText.replaceAll(" +", "") mustBe
        """<!DOCTYPE html>
          |
          |<html>
          |    <head>
          |        <meta charset="utf-8">
          |        <meta http-equiv="X-UA-Compatible" content="IE=edge">
          |        <meta name="viewport" content="width=device-width, initial-scale=i">
          |        <link rel="shortcut icon" href="#" />
          |        <title>Lipson</title>
          |    </head>
          |    <body>
          |        <h1>
          |            Hamm1ng C0des
          |        </h1>
          |        <h4>
          |            Enter a binary string as input.
          |            Click
          |                <a target="_blank" href="https://www.mathsisfun.com/binary-number-system.html">here</a>
          |                if you don't know what binary is or how it works.
          |        </h4>
          |        <label for="hamming-input">Enter binary string: </label><input id="hamming-input" type="text"
          |    placeholder="Binary string" autofocus>
          |        <h2 id="hamming-header">
          |        </h2>
          |        <h3 id="hamming-result">
          |        </h3>
          |        <p>&emsp;&emsp;In the mid 1940s a man name richard hamming worked on an electromechanical relay machine
          |            called the Bell Model V. Back then, computing machines were a lot slower. The Bell Model V was
          |            measured in cycles per second. To give some perspective, a modern CPU might be around 3.5ghz,
          |            which is approximately 3,500,000,000 cycles per second. Workers like Richard Hamming had to feed
          |            their program input into the machine on punched paper tape which was 7/8 of an inch wide and
          |            had up to 6 holes per row. Each hole would represent a bit of data, either a 0 if it were punched
          |            through, or a 1 if it wasn't. It was required that the Bell Model V be given input 24/7. Hamming
          |            and his coworkers, not wanting to work on evenings and weekends, set up a machine to feed jobs
          |            into the computer automatically. However, when the Bell Model V hit an error in the input, it
          |            would make a loud noise and stop all work indefinitely until one of the operators came in and
          |            fixed it. As you can imagine, this became very frustrating for Richard Hamming. He said to himself
          |            "Darn it, if the machine can detect an error, why can't it locate the position of the error and
          |            correct it?". He went to work on the problem and developed increasingly complex and powerful error
          |            correction algorithms. After a few years, in 1950, he published what's referred to now as the
          |            'Hamming code', which is a set of error correction codes that can be encoded into the data itself.
          |            It was such a good idea, most systems that utilize ecc (error correcting code) memory still encode
          |            data with hamming codes today, including dynamic random access memory, or RAM as a shorthand.
          |            This prevents electrical or magnetic interference from corrupting data. Your computer at home
          |            is almost certainly utilizing Hamming codes!
          |        </p>
          |        <p>
          |            So how do Hamming codes actually work?
          |        </p>
          |        <p>
          |            Imagine we have a 3 bit number representing a piece of data.
          |            We can represent this number as a cube where each bit represents an axis with each of the
          |            possibilities on one of the corners.
          |        </p>
          |        <pre>
          |            000---------001
          |            | \         | \
          |            | 100---------101
          |            |  |        |  |
          |            |  |        |  |
          |           010-|-------011 |
          |              \|          \|
          |              110---------111
          |        </pre>
          |        <p>
          |            Let's use the first two bits as data and the third bit as a parity bit.
          |            In this case, the parity would cause us to lose 4 data points,
          |            But it's worth it to be able to detect a single bit failure (which transforms
          |            an even count of 1's into an odd count of 1's). Let's mark all these odd numbers with a star.
          |        </p>
          |        <pre>
          |            000--------*001
          |            | \         | \
          |            |*100---------101
          |            |  |        |  |
          |            |  |        |  |
          |          *010-|-------011 |
          |              \|          \|
          |              110---------*111
          |        </pre>
          |        <p>
          |            Each odd (wrongly transmitted) is cornered by an even (correctly transmitted) number.
          |            so if we receive an odd number (like 100), we can be sure it's wrong. This is called 1 bit error detecting code.
          |            <br/><br/>
          |            Now let's remove another bit of data and use it for parity as well (leaving us with one bit of data and two bits of
          |            parity).
          |            Then 000 and 111 are both valid data representations, and all of the other possibilities aren't,
          |            leaving us with this cube.
          |        </p>
          |        <pre>
          |           000 --------*001
          |            | \         | \
          |            |*100--------*101
          |            |  |        |  |
          |            |  |        |  |
          |          *010-|------*011 |
          |              \|          \|
          |              *110--------111
          |        </pre>
          |        <p>
          |            If you receive a wrongly transmitted piece of data (like 100), that piece of data
          |            only has 1 valid neighbor (000 is valid while 110 and 101 are not). We can correct this piece of data to instead
          |            Transmit 000. This is called a 1 bit error correcting code. Note that a 1 bit error correcting code is also a
          |            2 bit error detection code. We can continue to increase the size of the data infinitely so long as we put the
          |            correct number
          |            of parity bits in.
          |            <br/><br/>
          |            To put it more generally, if you have n parity bits, you have an n bit error detecting code. if you have 2n bits,
          |            You have an n bit error correcting code. it goes up by powers of 2. Also you need to be sure the data is
          |            ordered such that the "valid" codes don't border one another.
          |        </p>
          |        <p>
          |            Let's put this into pseudo code, the general algorithm is as follows:
          |        </p>
          |        <p>
          |            1. Calculate the length of the hamming code
          |        </p>
          |            This is done using this formula:
          |        <pre>2<sup>p</sup> ≥ m+p+1</pre>
          |        Where m = the number of bits in the data, and p = the number of parity bits needed. This can also be solved using
          |        the logarithmic notation of that same formula which is this;
          |        <pre>p ≥ log<sub>2</sub>(m+p+1).</pre>
          |        How do we code this? Well you notice the ≥ symbol is used rather than the = symbol. That means this formula
          |        isn't an equality, which implies an iterator. In other words, it introduces the aspect of "time" to the equation.
          |        All you have to do is count upwards by 1 until you reach the first value that satisfies the equation for a given input.
          |        For example, for the binary input 1101, we can start with p=0.
          |        <pre>(2<sup>0</sup> ≥ 4+0+1) = (1 ≥ 5)</pre>
          |        False, 1 is not greater than or equal to 5! so that's not the answer. Let's go up by 1 making p=1.
          |        <pre>(2<sup>1</sup> ≥ 4+1+1) = (2 ≥ 5)</pre>
          |        Not yet, p=2...
          |        <pre>(2<sup>2</sup> ≥ 4+2+1) = (4 ≥ 7)</pre>
          |        ...Nope, p=3...
          |        <pre>(2<sup>3</sup> ≥ 4+3+1) = (8 ≥ 8)</pre>
          |        Yes! We found the lowest integer for which the given formula succeeds. As such, the binary string "1101" will need
          |        to encode 3 bits of data in order to have a functional error correcting code, making the total length be 7.
          |        Here's the code representing what I just described:
          |    <pre>
          |final def getNumberOfParityBits(binaryInput: String, acc: Int = 0): Int = {
          |    if (scala.math.pow(2, acc) >= binaryInput.length + acc + 1) {
          |        acc
          |    } else {
          |        getNumberOfParityBits(binaryInput, acc + 1)
          |    }
          |}
          |    </pre>
          |    As for why that particular formula is used, I'll come back to it. In the meantime, refer to this table to see the pattern
          |        that this formula produces.
          |        <table>
          |            <thead>
          |                <th>
          |                    <b>
          |                    Length of input
          |                    </b>
          |                </th>
          |                <th>
          |                    <b>
          |                    Parity bits needed
          |                    </b>
          |                </th>
          |                <th>
          |                    <b>
          |                    Total hamming code length
          |                    </b>
          |                </th>
          |            </thead>
          |            <tbody>
          |                <td>1</td>
          |                <td>2</td>
          |                <td>3</td>
          |            </tbody>
          |            <tbody>
          |                <td>2</td>
          |                <td>3</td>
          |                <td>5</td>
          |            </tbody>
          |            <tbody>
          |                <td>3</td>
          |                <td>3</td>
          |                <td>6</td>
          |            </tbody>
          |            <tbody>
          |                <td>4</td>
          |                <td>3</td>
          |                <td>7</td>
          |            </tbody>
          |            <tbody>
          |                <td>5</td>
          |                <td>4</td>
          |                <td>9</td>
          |            </tbody>
          |            <tbody>
          |                <td>6</td>
          |                <td>4</td>
          |                <td>10</td>
          |            </tbody>
          |            <tbody>
          |                <td>7</td>
          |                <td>4</td>
          |                <td>11</td>
          |            </tbody>
          |            <tbody>
          |                <td>8</td>
          |                <td>4</td>
          |                <td>12</td>
          |            </tbody>
          |        </table>
          |        etc...
          |        <p>
          |            2. insert the parity bits into the correct position
          |        </p>
          |        <p>
          |            Parity bits only go into positions that are a power of two. in other words, the 1st, 2nd, 4th, 8th, 16th, etc...
          |            Positions.
          |        </p>
          |        <table>
          |            <thead>
          |                <th>
          |                    <b>
          |                    input
          |                    </b>
          |                </th>
          |                <th>
          |                    <b>
          |                    Location of parity bits
          |                    </b>
          |                </th>
          |            </thead>
          |            <tbody>
          |                <td>11010011</td>
          |                <td>xx1x101x0011</td>
          |            </tbody>
          |        </table>
          |        <div>
          |            <div>
          |                <p>
          |                    Can you see the pattern? The positions of the parity bits (step 2) will help you see how many parity bits
          |                    were needed (step
          |                    1).
          |                </p>
          |            </div>
          |        </div>
          |        <p>
          |            3. Calculate the correct values for the parity bits
          |        </p>
          |        <div>
          |            <div>
          |                <p>
          |                    The bits that alter a parity bit are determined by the position of the parity bit. You'll have to know how
          |                    to do
          |                    <a target="_blank" href="https://teachwithict.weebly.com/binary-addition.html">Binary addition</a>
          |                    To understand this part. If it's in the 1st position, then it will start with the first bit and add 1 bit,
          |                    skip 1 bit, etc...
          |                    if it's in position 2, then it will start with the second bit and add 2 bits, skip 2
          |                    Bits, etc... Position 4 starts with the fourth bit and adds 4, skips 4, etc... And so
          |                    On and so forth. The mapTotalSeasonNumberToArrayOfIndividualSeasonNumbers's will count as 0's. This is why we used that particular formula in step 1. Counting
          |                    by powers of 2 in a base 2 number system (binary) allows us to encode data as either even and correct (0) or
          |                    odd and incorrect (1). It all starts with the left hand side of that function that states 2<sup>p</sup>.
          |                </p>
          |            </div>
          |        </div>
          |        <div>
          |            <div>
          |                <table>
          |                    <thead>
          |                        <th>
          |                            <b>
          |                            Not calculated hamming code
          |                            </b>
          |                        </th>
          |                        <th>
          |                            <b>
          |                            Bits to add together
          |                            </b>
          |                        </th>
          |                        <th>
          |                            <b>
          |                            Result
          |                            </b>
          |                        </th>
          |                        <th>
          |                            <b>
          |                            Hamming code with added bit
          |                            </b>
          |                        </th>
          |                    </thead>
          |                    <tbody>
          |                        <td>
          |                            <mark>mapTotalSeasonNumberToArrayOfIndividualSeasonNumbers</mark>
          |                            x1x101x0011
          |                        </td>
          |                        <td>
          |                            <mark>mapTotalSeasonNumberToArrayOfIndividualSeasonNumbers</mark>
          |                            mapTotalSeasonNumberToArrayOfIndividualSeasonNumbers
          |                            <mark>1</mark>
          |                            mapTotalSeasonNumberToArrayOfIndividualSeasonNumbers
          |                            <mark>1</mark>
          |                            0
          |                            <mark>1</mark>
          |                            mapTotalSeasonNumberToArrayOfIndividualSeasonNumbers
          |                            <mark>0</mark>
          |                            0
          |                            <mark>1</mark>
          |                            1
          |                        </td>
          |                        <td>0</td>
          |                        <td>
          |                            <mark>0</mark>
          |                            x1x101x0011
          |                        </td>
          |                    </tbody>
          |                    <tbody>
          |                        <td>0
          |                            <mark>mapTotalSeasonNumberToArrayOfIndividualSeasonNumbers</mark>
          |                            1x101x0011
          |                        </td>
          |                        <td>0
          |                            <mark>x1</mark>
          |                            x1
          |                            <mark>01</mark>
          |                            x0
          |                            <mark>01</mark>
          |                            1
          |                        </td>
          |                        <td>1</td>
          |                        <td>0
          |                            <mark>1</mark>
          |                            1x101x0011
          |                        </td>
          |                    </tbody>
          |                    <tbody>
          |                        <td>011
          |                            <mark>mapTotalSeasonNumberToArrayOfIndividualSeasonNumbers</mark>
          |                            101x0011
          |                        </td>
          |                        <td>011
          |                            <mark>x101</mark>
          |                            x001
          |                            <mark>1</mark>
          |                        </td>
          |                        <td>1</td>
          |                        <td>011
          |                            <mark>1</mark>
          |                            101x0011
          |                        </td>
          |                    </tbody>
          |                    <tbody>
          |                        <td>0111101
          |                            <mark>mapTotalSeasonNumberToArrayOfIndividualSeasonNumbers</mark>
          |                            0011
          |                        </td>
          |                        <td>0111101
          |                            <mark> x0011</mark>
          |                        </td>
          |                        <td>0</td>
          |                        <td>0111101
          |                            <mark>0</mark>
          |                            0011
          |                        </td>
          |                    </tbody>
          |                </table>
          |                <p>
          |                    So the final hamming code for the input 11010011 will be 011110100011
          |                </p>
          |                <span>Written in Scala
          |                    <a href="https://github.com/blipson/benlipson.io/blob/master/app/service/HammingService.scala" target="_blank">Wanna see the code?</a>
          |                </span>
          |            </div>
          |        </div>
          |    </body>
          |    <script type="text/javascript">
          |        document.getElementById("hamming-input").onkeyup = (e) => {
          |            const xmlHttp = new XMLHttpRequest();
          |            const hammingInput = document.getElementById("hamming-input")
          |            const hammingHeader = document.getElementById("hamming-header")
          |            const hammingResult = document.getElementById("hamming-result")
          |            xmlHttp.onreadystatechange = () => {
          |                if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
          |                    hammingHeader.innerHTML = "HERE iS YoUR HAMMiNG CoDE BEEP BooP";
          |                    hammingResult.innerHTML = JSON.parse(xmlHttp.responseText).hamming_code;
          |                } else if (xmlHttp.readyState === 4 && xmlHttp.status !== 200) {
          |                    hammingHeader.innerHTML = "*BZZT* ERRoR! CAN. NoT. CoMPUTE. *SAD BooP*";
          |                    hammingResult.innerHTML = xmlHttp.responseText.split(":")[1];
          |                }
          |            }
          |            if (hammingInput.value) {
          |                xmlHttp.open("GET", window.location.href.replace("/projects", "") + "?input=" + hammingInput.value, true);
          |                xmlHttp.send(null);
          |            } else {
          |                hammingHeader.innerHTML = "";
          |                hammingResult.innerHTML = "";
          |
          |            }
          |        }
          |    </script>
          |</html>
          |""".stripMargin.replaceAll(" +", "")
    }

    "should display the 'Graph TV' project page" in {
      val controller = new ProjectsController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.graphTv().apply(FakeRequest())
      val bodyText: String = contentAsString(result)
      bodyText.replaceAll(" +", "") mustBe
        """<!DOCTYPE html>
          |
          |<html lang="en">
          |    <link rel="stylesheet" href="//cdn.jsdelivr.net/chartist.js/latest/chartist.min.css">
          |    <script src="//cdn.jsdelivr.net/chartist.js/latest/chartist.min.js"></script>
          |    <head>
          |        <style>
          |            .ct-series.ct-series-p {
          |                stroke: #2f8c73
          |            }
          |
          |            .ct-series.ct-series-q {
          |                stroke: #ff6b72
          |            }
          |
          |            .ct-series.ct-series-r {
          |                stroke: #ffae00
          |            }
          |
          |            .ct-series.ct-series-s {
          |                stroke: #3a5f97
          |            }
          |
          |            .ct-series.ct-series-t {
          |                stroke: #a35f9a
          |            }
          |
          |            .ct-series.ct-series-t {
          |                stroke: #b0af67
          |            }
          |
          |            .ct-series.ct-series-u {
          |                stroke: #8134ad
          |            }
          |
          |            .ct-series.ct-series-v {
          |                stroke: #5a9471
          |            }
          |
          |            .ct-series.ct-series-w {
          |                stroke: #7d7d7d
          |            }
          |
          |            .ct-series.ct-series-mapTotalSeasonNumberToArrayOfIndividualSeasonNumbers {
          |                stroke: #202020
          |            }
          |
          |            .ct-series.ct-series-y {
          |                stroke: #00c4ff
          |            }
          |
          |            .ct-series.ct-series-z {
          |                stroke: #914d67
          |            }
          |        </style>
          |        <title>Lipson</title>
          |        <link rel="shortcut icon" href="#" />
          |    </head>
          |    <body>
          |        <h1>
          |            Graph TV
          |        </h1>
          |        <h4>
          |            Enter the name of a TV show to get metrics about it. It'll graph IMDB ratings over time, with different seasons in different colors. You can hover over the data points for additional info about each episode.
          |        </h4>
          |        <label for="graphtv-input"></label>
          |        <input id="graphtv-input" class="input" type="text" placeholder="TV Show" autofocus>
          |        <span id="loading" style="display: none">Loading...</span>
          |        <span id="not-found" style="display: none">TV show not found.</span>
          |        <h2 id="graphtv-header">
          |        </h2>
          |        <div id="charterino" style="height: 50vh" class="ct-chart ct-perfect-fourth"></div>
          |        <div id="tooltip" style="display: none; position: fixed; bottom: 550px; left: 50px;"></div>
          |        <span>Written in JavaScript
          |            <a href="https://github.com/blipson/benlipson.io/blob/master/app/views/graphTv.scala.html" target="_blank">Wanna see the code?</a>
          |        </span>
          |    </body>
          |    <script type="text/javascript">
          |        const prependNullsForSeason = (numberOfEpisodesInPreviousSeason) => [...Array(numberOfEpisodesInPreviousSeason).keys()].map(() => null);
          |
          |        const getMaxSeasonNumber = (seasons) => seasons
          |                .map(season => parseInt(season.episodes[0].seasonNumber))
          |                .reduce((previousMax, curr) => Math.max(previousMax, curr), 0);
          |
          |        const getSeasonNumber = (accLen, season, seasonNumbersInYears) => seasonNumbersInYears ?
          |                (accLen >= 2 ? accLen : accLen + 1) :
          |                parseInt(season.episodes[0].seasonNumber);
          |
          |        const prependNullsForSeasons = (seasons, seasonNumbersInYears) => seasons.reduce((acc, season) => {
          |            const seasonNumber = getSeasonNumber(Object.keys(acc).length, season, seasonNumbersInYears);
          |            if (seasonNumber === 1) {
          |                acc[seasonNumber] = prependNullsForSeason(0);
          |            }
          |            if (seasonNumber < seasonNumbersInYears ? seasons.length : getMaxSeasonNumber(seasons)) {
          |                acc[seasonNumber + 1] = prependNullsForSeason(season.episodes.filter(episode => episode.imDbRating !== "").length + acc[seasonNumber].length);
          |            }
          |            return acc;
          |        }, {});
          |
          |        const defineMetadataForDataPoint = (season) => season.episodes
          |                .filter(episode => episode.imDbRating !== "")
          |                .map(episode => {
          |                    return {
          |                        number: `Season ${episode.seasonNumber}, Episode ${episode.episodeNumber}`,
          |                        rating: episode.imDbRating,
          |                        released: episode.released,
          |                        title: episode.title,
          |                        value: episode.imDbRating
          |                    }
          |                });
          |
          |        const getSeasonNumberForSeries = (seasonNumbersInYears, season, index) => seasonNumbersInYears ? index + 1 : season.episodes[0].seasonNumber;
          |
          |        const getSeries = (seasons, seasonNumbersInYears) => seasons.map((season, index) => [
          |            ...prependNullsForSeasons(seasons, seasonNumbersInYears)[getSeasonNumberForSeries(seasonNumbersInYears, season, index)],
          |            ...defineMetadataForDataPoint(season)
          |        ]);
          |
          |        const getLowestImdbRatingForSeries = (seasons) => seasons.map(season =>
          |                season.episodes
          |                        .reduce((previousMinThisSeason, curr) =>
          |                                Math.min(previousMinThisSeason.imDbRating, curr.imDbRating), 0))
          |                .reduce((previousMin, curr) =>
          |                        Math.min(previousMin, curr), 0)
          |
          |        const makeChart = (seasons) => {
          |            // The APIs can return season numbers either in sequential format [1, 2, 3, etc...] or in the format of the year released [2006, 2007, 2008, etc...].
          |            const seasonNumbersInYears = parseInt(seasons[0].episodes[0].seasonNumber) >= 1953; // the year the television was invented
          |            return new Chartist.Line('.ct-chart', {
          |                series: getSeries(seasons, seasonNumbersInYears),
          |            }, {
          |                low: getLowestImdbRatingForSeries(seasons)
          |            });
          |        }
          |
          |        const handleChartDrawEvent = (data) => {
          |            if (data.type === 'point') {
          |                data.element.attr({
          |                    title: data.series[data.index].title,
          |                    value: data.series[data.index].value,
          |                    number: data.series[data.index].number,
          |                    released: data.series[data.index].released,
          |                    rating: data.series[data.index].rating
          |                });
          |            }
          |        }
          |
          |        const disableElement = (element) => {
          |            element.disabled = true;
          |        }
          |
          |        const enableElement = (element) => {
          |            element.disabled = false;
          |        }
          |
          |        const showElement = (element) => {
          |            element.style.display = '';
          |        }
          |
          |        const hideElement = (element) => {
          |            element.style.display = 'none';
          |        }
          |
          |        const setContentOfElement = (element, content) => {
          |            element.innerHTML = content;
          |        }
          |
          |        const graphTvInput = document.getElementById("graphtv-input");
          |        const loading = document.getElementById("loading");
          |        const notFound = document.getElementById("not-found");
          |
          |        const handleDataPointHoverEvents = (dataPoint) => {
          |            const tooltip = document.getElementById("tooltip");
          |            dataPoint.onmouseover = (e) => {
          |                tooltip.style.top = `${e.pageY + 25}px`;
          |                tooltip.style.left = `${e.pageX - 60}px`;
          |                showElement(tooltip);
          |                setContentOfElement(tooltip, `${dataPoint.getAttribute("title")}<br/>` +
          |                        `${dataPoint.getAttribute("number")}<br/>` +
          |                        `${dataPoint.getAttribute("released")}<br/>` +
          |                        `IMDB Rating: ${dataPoint.getAttribute("rating")}`)
          |            }
          |            dataPoint.onmouseout = () => {
          |                hideElement(tooltip);
          |            }
          |        }
          |
          |        const handleChartCreatedEvent = (seasons) => {
          |            hideElement(loading);
          |            enableElement(graphTvInput);
          |            setContentOfElement(document.getElementById("graphtv-header"), seasons[0].title);
          |            const dataPoints = document.getElementsByClassName("ct-point");
          |            Array.from(dataPoints).map(dataPoint => {
          |                handleDataPointHoverEvents(dataPoint);
          |            })
          |        }
          |
          |        const makeGraph = (seasons) => {
          |            seasons.sort((a, b) => a.episodes[0].season - b.episodes[0].season);
          |            let chart = makeChart(seasons);
          |
          |            chart.on('draw', (data) => {
          |                handleChartDrawEvent(data);
          |            });
          |
          |            chart.on('created', () => {
          |                handleChartCreatedEvent(seasons);
          |            });
          |        }
          |
          |        const searchShows = async (searchTerm) => {
          |            try {
          |                const matchingShow = await fetch(`https://imdb-api.com/en/API/searchSeries/k_9fbi3vm5/${searchTerm}`)
          |                        .then(response => response.json())
          |                        .results
          |                        .filter(show => show.title.toLowerCase() === searchTerm.toLowerCase());
          |                return {
          |                    matchingShow: matchingShow.length ? matchingShow[0] : {},
          |                    api: "imdb-api"
          |                }
          |            } catch (exception) {
          |                const matchingShow = await fetch(`https://www.omdbapi.com/?t=${searchTerm}&type=series&apikey=4f09f372`)
          |                    .then(response => response.json());
          |                return {
          |                    matchingShow: matchingShow.Error ? {}: matchingShow,
          |                    api: "omdb"
          |                }
          |            }
          |        }
          |
          |        const getShowSeasonNumbersFromImdbApi = async (id) => {
          |            const showDetailResults = await fetch(`https://imdb-api.com/en/API/Title/k_9fbi3vm5/${id}`).then(response => response.json());
          |            return showDetailResults.tvSeriesInfo.seasons;
          |        }
          |
          |        const getShowSeasonDetailsFromImdbApi = async (id, seasonNumbers) => {
          |            return Promise.all(seasonNumbers.map(seasonNumber => {
          |                return fetch(`https://imdb-api.com/en/API/SeasonEpisodes/k_9fbi3vm5/${id}/${seasonNumber}`)
          |                        .then(response => response.json())
          |            }));
          |        }
          |
          |        const getShowSeasonDetailsFromOmdb = (searchTerm, seasonNumbers) => {
          |            return Promise.all(seasonNumbers.map(seasonNumber => {
          |                return fetch(`https://www.omdbapi.com/?t=${searchTerm}&Season=${seasonNumber}&type=series&apikey=4f09f372`)
          |                        .then(response => response.json())
          |            }));
          |        }
          |
          |        const foundMatchingShow = (show) => {
          |            return show.matchingShow && Object.keys(show.matchingShow).length !== 0 && show.matchingShow.constructor === Object;
          |        }
          |
          |        const mapOmdbResultsToImdbApiFormat = (seasons) => {
          |            return seasons.map(season => {
          |                return {
          |                    title: season.Title,
          |                    fullTitle: season.Title,
          |                    type: "TVSeries",
          |                    episodes: season.Episodes.map(episode => {
          |                        return {
          |                            seasonNumber: season.Season,
          |                            episodeNumber: episode.Episode,
          |                            title: episode.Title,
          |                            released: episode.Released,
          |                            imDbRating: episode.imdbRating
          |                        }
          |                    })
          |                }
          |            });
          |        }
          |
          |        const mapTotalSeasonNumberToArrayOfIndividualSeasonNumbers = (totalSeasonNumber) => {
          |            return Array.from({ length: totalSeasonNumber }, (_, index) => index + 1);
          |        }
          |
          |        const handleEnterKeyPressed = async () => {
          |            showElement(loading);
          |            hideElement(notFound);
          |            disableElement(graphTvInput);
          |            const show = await searchShows(graphTvInput.value);
          |            if (foundMatchingShow(show)) {
          |                if (show.api === "imdb-api") {
          |                    getShowSeasonDetailsFromImdbApi(show.matchingShow.id, await getShowSeasonNumbersFromImdbApi(show.matchingShow.id)).then(seasons => {
          |                        makeGraph(seasons);
          |                    });
          |                } else {
          |                    getShowSeasonDetailsFromOmdb(
          |                        graphTvInput.value,
          |                        mapTotalSeasonNumberToArrayOfIndividualSeasonNumbers(show.matchingShow.totalSeasons)
          |                    ).then(seasons => {
          |                        makeGraph(mapOmdbResultsToImdbApiFormat(seasons));
          |                    });
          |                }
          |            } else {
          |                hideElement(loading);
          |                showElement(notFound);
          |                enableElement(graphTvInput);
          |            }
          |        }
          |
          |        graphTvInput.onkeyup = async (e) => {
          |            if (e.code === "Enter") {
          |                await handleEnterKeyPressed();
          |            }
          |        };
          |    </script>
          |</html>
          |""".stripMargin.replaceAll(" +", "")
    }
  }
}
