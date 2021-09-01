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
          |                    On and so forth. The x's will count as 0's. This is why we used that particular formula in step 1. Counting
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
          |                            <mark>x</mark>
          |                            x1x101x0011
          |                        </td>
          |                        <td>
          |                            <mark>x</mark>
          |                            x
          |                            <mark>1</mark>
          |                            x
          |                            <mark>1</mark>
          |                            0
          |                            <mark>1</mark>
          |                            x
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
          |                            <mark>x</mark>
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
          |                            <mark>x</mark>
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
          |                            <mark>x</mark>
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
          |    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
          |    <script src="https://cdn.jsdelivr.net/chartist.js/0.9.5/chartist.min.js"></script>
          |    <link href="https://cdn.jsdelivr.net/chartist.js/0.9.5/chartist.min.css" rel="stylesheet" />
          |    <link rel="stylesheet" href="/static/graphTv.css">
          |    <body>
          |        <h1>
          |            Graph TV
          |        </h1>
          |        <h4>
          |            Enter the name of a TV show to get metrics about it. It'll graph IMDB ratings over time, with different seasons in different colors. You can hover over the data points for additional info about each episode.
          |        </h4>
          |        <input id="graphTv-input" type="text" placeholder="TV Show" autofocus>
          |        <h2 id="graphTv-header">
          |        </h2>
          |        <div id="charterino" class="ct-chart ct-perfect-fourth"></div>
          |        <span>Written in JavaScript
          |            <a href="/graphtvcode">Wanna see the code?</a>
          |        </span>
          |    </body>
          |    <script type="text/javascript">
          |        function makeGraph(s) {
          |            s.sort(function (a, b) {
          |                return a.Season - b.Season;
          |            });
          |
          |            s[s.length - 1].Episodes = s[s.length - 1].Episodes.filter(function (e) {
          |                return e.imdbRating !== 'N/A';
          |            });
          |
          |            s = s.filter(function (e) {
          |                return e.Episodes.length;
          |            });
          |
          |            let finalSeries = [];
          |            let season = [];
          |            let prepend = 0;
          |            let finalLabels = [];
          |            let episodeNumber = 0;
          |            let lowest = 10;
          |
          |            for (let i = 0; i < s.length; i++) {
          |                if (i !== 0) {
          |                    prepend += s[i - 1].Episodes.length;
          |                }
          |
          |                for (let k = 0; k < prepend; k++) {
          |                    season.push(null);
          |                }
          |
          |                for (let j = 0; j < s[i].Episodes.length; j++) {
          |                    if (s[i].Episodes[j].imdbRating < lowest) {
          |                        lowest = Math.floor(s[i].Episodes[j].imdbRating);
          |                    }
          |
          |                    season.push({
          |                        value: s[i].Episodes[j].imdbRating,
          |                        number: 'Season ' + (i + 1).toString() + ', Episode ' + (j + 1).toString(),
          |                        title: s[i].Episodes[j].Title,
          |                        released: s[i].Episodes[j].Released,
          |                        rating: s[i].Episodes[j].imdbRating
          |                    });
          |
          |                    episodeNumber++;
          |                    finalLabels.push(null);
          |                }
          |
          |                finalSeries.push(season);
          |                season = [];
          |            }
          |
          |            let chart = new Chartist.Line('.ct-chart', {
          |                labels: finalLabels,
          |                series: finalSeries,
          |            }, {
          |                low: lowest
          |            });
          |
          |            let seq = 0,
          |                    delays = 10,
          |                    durations = 100;
          |
          |            chart.on('created', function () {
          |                seq = 0;
          |            });
          |
          |            chart.on('draw', function (data) {
          |                seq++;
          |
          |                if (data.type === 'line') {
          |                    data.element.animate({
          |                        opacity: {
          |                            begin: seq * delays + 1000,
          |                            dur: durations,
          |                            from: 0,
          |                            to: 1
          |                        }
          |                    });
          |                } else if (data.type === 'label' && data.axis === 'x') {
          |                    data.element.animate({
          |                        y: {
          |                            begin: seq * delays,
          |                            dur: durations,
          |                            from: data.y + 100,
          |                            to: data.y,
          |                            easing: 'easeOutQuart'
          |                        }
          |                    });
          |                } else if (data.type === 'label' && data.axis === 'y') {
          |                    data.element.animate({
          |                        x: {
          |                            begin: seq * delays,
          |                            dur: durations,
          |                            from: data.x - 100,
          |                            to: data.x,
          |                            easing: 'easeOutQuart'
          |                        }
          |                    });
          |                } else if (data.type === 'point') {
          |                    data.element.attr({
          |                        title: data.series[data.index].title,
          |                        value: data.series[data.index].value,
          |                        number: data.series[data.index].number,
          |                        released: data.series[data.index].released,
          |                        rating: data.series[data.index].rating
          |                    });
          |
          |                    data.element.animate({
          |                        x1: {
          |                            begin: seq * delays,
          |                            dur: durations,
          |                            from: data.x - 10,
          |                            to: data.x,
          |                            easing: 'easeOutQuart'
          |                        },
          |                        x2: {
          |                            begin: seq * delays,
          |                            dur: durations,
          |                            from: data.x - 10,
          |                            to: data.x,
          |                            easing: 'easeOutQuart'
          |                        },
          |                        opacity: {
          |                            begin: seq * delays,
          |                            dur: durations,
          |                            from: 0,
          |                            to: 1,
          |                            easing: 'easeOutQuart'
          |                        }
          |                    });
          |                } else if (data.type === 'grid') {
          |                    let pos1Animation = {
          |                        begin: seq * delays,
          |                        dur: durations,
          |                        from: data[data.axis.units.pos + '1'] - 30,
          |                        to: data[data.axis.units.pos + '1'],
          |                        easing: 'easeOutQuart'
          |                    };
          |
          |                    let pos2Animation = {
          |                        begin: seq * delays,
          |                        dur: durations,
          |                        from: data[data.axis.units.pos + '2'] - 100,
          |                        to: data[data.axis.units.pos + '2'],
          |                        easing: 'easeOutQuart'
          |                    };
          |
          |                    let animations = {};
          |                    animations[data.axis.units.pos + '1'] = pos1Animation;
          |                    animations[data.axis.units.pos + '2'] = pos2Animation;
          |                    animations['opacity'] = {
          |                        begin: seq * delays,
          |                        dur: durations,
          |                        from: 0,
          |                        to: 1,
          |                        easing: 'easeOutQuart'
          |                    };
          |
          |                    data.element.animate(animations);
          |                }
          |            });
          |
          |            chart.on('created', function () {
          |                $('.ct-point').on('mouseover', function (e) {
          |                    const mouseX = e.pageX - 60;
          |                    const mouseY = e.pageY + 25;
          |
          |                    $('#tooltip').css({ 'top': mouseY, 'left': mouseX, 'display': '', 'background': 'grey' })
          |                            .html($(this).attr('title') + '<br/>'
          |                                    + $(this).attr('number') + '<br/>'
          |                                    + $(this).attr('released') + '<br/>'
          |                                    + 'IMDB Rating: ' + $(this).attr('rating'));
          |                });
          |
          |                $('.ct-point').on('mouseout', function (e) {
          |                    $('#tooltip').css('display', 'none');
          |                });
          |            });
          |        }
          |
          |        $('#graphTv-input').on('keyup', function (e) {
          |            if (e.keyCode == 13) {
          |                let xmlHttp = new XMLHttpRequest();
          |
          |                xmlHttp.onreadystatechange = function () {
          |                    $('#graphTv-header').text('Searching...');
          |
          |                    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
          |                        $('#graphTv-header').text('');
          |                        const res = JSON.parse(xmlHttp.response);
          |
          |                        if (res.totalSeasons) {
          |                            $('#charterino').css('display', '');
          |                            const totalSeasons = res.totalSeasons;
          |                            let series = [];
          |                            let seasons = [];
          |                            let nextXhr = [];
          |
          |                            for (let i = 1; i <= totalSeasons; i++) {
          |                                (function (i) {
          |                                    nextXhr[i] = new XMLHttpRequest();
          |                                    url = 'https://www.omdbapi.com/?t=' + $('#graphTv-input').val() + '&Season=' + i + '&apikey=4f09f372'
          |                                    nextXhr[i].open('GET', url, true);
          |
          |                                    nextXhr[i].onreadystatechange = function () {
          |                                        if (nextXhr[i].readyState === 4 && nextXhr[i].status === 200) {
          |                                            const nextRes = JSON.parse(nextXhr[i].response);
          |                                            seasons.push(nextRes);
          |
          |                                            if (seasons.length >= totalSeasons) {
          |                                                if (seasons[0].Response !== 'False') {
          |                                                    $('#graphTv-header').text(seasons[0].Title);
          |
          |                                                    if (seasons[seasons.length - 1].Episodes[0].Released === 'N/A') {
          |                                                        seasons.splice(-1, 1);
          |                                                    }
          |
          |                                                    makeGraph(seasons);
          |                                                } else {
          |                                                    $('#graphTv-header').text('TV show not found.');
          |                                                }
          |                                            }
          |                                        }
          |                                    };
          |
          |                                    nextXhr[i].send();
          |                                })(i);
          |                            }
          |                        } else {
          |                            $('#graphTv-header').text('TV show not found. You may have searched for a movie by mistake.');
          |                            $('#charterino').css('display', 'none');
          |                        }
          |                    }
          |                }
          |
          |                xmlHttp.open('GET', 'https://www.omdbapi.com/?t=' + $('#graphTv-input').val() + '&apikey=4f09f372', true);
          |                xmlHttp.send(null);
          |            }
          |        });
          |    </script>
          |</html>
          |""".stripMargin.replaceAll(" +", "")
    }
  }
}
