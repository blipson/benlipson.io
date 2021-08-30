import controllers.ProjectsController
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
          |        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
          |        <hi>
          |            Hamm1ng C0des
          |        </hi>
          |        <h4>
          |            Enter a binary string as input and hit enter
          |            <p>Click
          |                <a target="_blank" href="https://www.mathsisfun.com/binary-number-system.html">here</a>
          |                if you don't know what binary is or how it works.</p>
          |        </h4>
          |        <label for="hamming-input">Enter binary string: </label><input id="hamming-input" type="text"
          |    placeholder="Text input" autofocus>
          |        <h2 id="hamming-header">
          |        </h2>
          |        <h3 id="hamming-result">
          |        </h3>
          |        <p>&emsp;&emsp;in the mid i940s a man name richard hamming worked on an electromechanical relay machine
          |            Called the bell model V. Back then, computing machines were a lot slower. The bell model v was
          |            Measured in cycles per second. To give some perspective, a modern cpu might be around 3.5ghz,
          |            Which is approximately 3,500,000,000 cycles per second. Workers like richard hamming had to feed
          |            Their program input into the machine on punched paper tape which was 7/8 of an inch wide and
          |            Had up to 6 holes per row. Each hole would represent a bit of data, either a 0 if it were punched
          |            Through, or a 1 if it wasn't. It was required that the bell model v be given input 24/7. Hamming
          |            And his coworkers, not wanting to work on evenings and weekends, set up a machine to feed jobs
          |            into the computer automatically. However, when the bell model v hit an error in the input, it
          |            Would make a loud noise and stop all work indefinitely until one of the operators came in and
          |            Fixed it. As you can imagine, this became very frustrating for Richard Hamming. He said to himself
          |            "Damn it, if the machine can detect an error, why can't it locate the position of the error and
          |            Correct it?" he went to work on the problem and developed increasingly complex and powerful error
          |            Correction algorithms. After a few years, in 1950, he published what's referred to now as the
          |            'hamming code', which is a set of error correction codes that can be encoded into the data itself.
          |            it was such a good idea, most systems that utilize ecc (error correcting code) memory still encode
          |            Data with hamming codes today, including dynamic random access memory, or RAM as a shorthand.
          |            This prevents electrical or magnetic interference from corrupting data. Your computer at home
          |            is almost certainly utilizing hamming codes!
          |        </p>
          |        <p>
          |            So how do hamming codes actually work?
          |        </p>
          |        <p>
          |            imagine we have a 3 bit number representing a piece of data.
          |            We can represent this number as a cube where each bit represents an axis with each of the
          |            Possibilities on one of the corners.
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
          |            in this case, the parity would cause us to lose 4 data points,
          |            But it's worth it to be able to detect a single bit failure (which transforms
          |            An even count of 1's into an odd count of 1's). Let's mark all these odd numbers with a star.
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
          |            So if we receive an odd number (like 100), we can be sure it's wrong. This is called 1 bit error detecting code.
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
          |            if you recieve a wrongly transmitted piece of data (like 100), that piece of data
          |            only has 1 valid neighbor (000 is valid while 110 and 101 are not). We can correct this piece of data to instead
          |            Transmit 000. This is called a 1 bit error correcting code. Note that a 1 bit error correcting code is also a
          |            2 bit error detection code. We can continue to increase the size of the data infinitely so long as we put the
          |            correct number
          |            of parity bits in.
          |            <br/><br/>
          |            To put it more generally, if you have n parity bits, you have an n bit error detecting code. if you have 2n bits,
          |            You have an n bit error correcting code. it goes up by powers of 2. Also you need to be sure the data is
          |            ordered such that the "Valid" codes don't border one another.
          |        </p>
          |        <p>
          |            Let's put this into pseudo code, the general algorithm is as follows:
          |        </p>
          |        <p>
          |            1. Calculate the length of the hamming code
          |        </p>
          |        <p>
          |            Accumulate the number of parity bits needed by incrementing until the number of parity bits plus the length of input
          |            is greater
          |            Than 2 raised to the number of parity bits.
          |        </p>
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
          |                <td>3</td>
          |                <td>4</td>
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
          |                <td>4</td>
          |                <td>8</td>
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
          |                    <a target="_blank" href="http://web.math.princeton.edu/math_alive/i/Labi/BinAdd.html">Binary addition</a>
          |                    To understand this part. if it's in the 1st position, then it will start with the first bit and add 1 bit,
          |                    skip 1 bit, etc...
          |                    if it's in position 2, then it will start with the second bit and add 2 bits, skip 2
          |                    Bits, etc... Position 4 starts with the fourth bit and adds 4, skips 4, etc... And so
          |                    On and so forth. The x's will count as 0's.
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
          |                    <a href="/hammingcodecode">Wanna see the code?</a>
          |                </span>
          |            </div>
          |        </div>
          |    </body>
          |    <script type="text/javascript">
          |        $("#hamming-input").on('keyup', function (e) {
          |            if (e.keyCode === 13) {
          |                const xmlHttp = new XMLHttpRequest();
          |                xmlHttp.onreadystatechange = function () {
          |                    $("#hamming-header").text("CALCULATiNG...");
          |                    $("#hamming-result").text("");
          |                    if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
          |                        $("#hamming-header").text("HERE iS YoUR HAMMiNG CoDE BEEP BooP");
          |                        $("#hamming-result").text(JSON.parse(xmlHttp.responseText).hamming_code);
          |                    } else if (xmlHttp.readyState === 4 && xmlHttp.status !== 200) {
          |                        $("#hamming-header").text("*BZZT* ERRoR! CAN. NoT. CoMPUTE. *SAD BooP*");
          |                        $("#hamming-result").text(JSON.parse(xmlHttp.responseText).error.toUpperCase());
          |                    }
          |                }
          |                xmlHttp.open("GET", window.location.href.replace("/projects", "") + "?input=" + $("#hamming-input").val(), true);
          |                xmlHttp.send(null);
          |            }
          |        });
          |    </script>
          |</html>
          |""".stripMargin.replaceAll(" +", "")
    }
  }
}
