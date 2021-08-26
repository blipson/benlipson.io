import controllers.BlogController
import org.scalatestplus.play.PlaySpec
import play.api.mvc.Result
import play.api.test.Helpers.{contentAsString, defaultAwaitTimeout}
import play.api.test.{FakeRequest, Helpers}

import scala.concurrent.Future

class BlogControllerTest extends PlaySpec {
  "Blog controller" should {
    "should display the blog page" in {
      val controller = new BlogController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.blog().apply(FakeRequest())
      val bodyText: String = contentAsString(result)
      bodyText.replaceAll(" +", "") mustBe
        """<!DOCTYPE html>
          |
          |<html lang="en">
          |  <head>
          |    <meta charset="utf-8">
          |    <meta http-equiv="X-UA-Compatible" content="IE=edge">
          |    <meta name="viewport" content="width=device-width, initial-scale=1">
          |    <link rel="shortcut icon" href="#" />
          |    <title>Lipson</title>
          |  </head>
          |  <body>
          |        <h1>Blog</h1>
          |        <ul>
          |            <li>
          |                <a href="/blog/renderer/drawinglines">Building a 3D Renderer From Scratch, Part 2: Drawing Lines</a>
          |                <br><i>
          |                07:45 PM - 24 February, 2020
          |            </i>
          |            </li>
          |            <li>
          |                <a href="/blog/renderer/tgaimages">Building a 3D Renderer From Scratch, Part 1: TGA Images</a>
          |                <br><i>
          |                04:30 PM - 19 February, 2020
          |            </i>
          |            </li>
          |            <li>
          |                <a href="/blog/graphicsnotes">Graphics Programming Notes</a>
          |                <br><i>
          |                04:30 PM - 06 November, 2019
          |            </i>
          |                <ul>
          |                    <li>Every few months I spend about a week diving into graphics programming stuff for fun. The problem: each time I've forgotten so much about it that I have to relearn a lot of what I previously already looked at. While each subsequent time is getting faster and faster, it's still rather annoying. So I've compiled this list of things that I seem to have to remind myself of every time in the hopes that it'll go faster in the future.</li>
          |                </ul>
          |            </li>
          |            <li>
          |                <a href="/blog/ongod">On God</a>
          |                <br><i>
          |                02:30 AM - 30 July, 2019
          |            </i>
          |                <ul>
          |                    <li>I've spent a lot of time on this one.</li>
          |                </ul>
          |            </li>
          |            <li>
          |                <a href="/blog/functionalenlightenment">I Pushed My Feeble Imperative Consciousness to a New Plane of Existence and So Can You</a>
          |                <br><i>
          |                    12:30 AM - 15 December, 2018
          |                </i>
          |                <ul>
          |                    <li>The internet is just tubes, our brains are disjoint, consciousness and substance are derived from monadic structures, and infinite break points.</li>
          |                </ul>
          |            </li>
          |            <li>
          |                <a href="/blog/onsports">On Sports</a>
          |                <br><i>
          |                    11:30 PM - 15 April, 2018
          |                </i>
          |                <ul>
          |                    <li>I love sports. But I have a gut feeling that maybe I shouldn't. So I tasked myself with investigating why I feel this way and what I should do about it. Then I wrote it down for you to read! I also dunk on utilitarianism, so get ready for some fun ranting.</li>
          |                </ul>
          |            </li>
          |            <li>
          |                <a href="/blog/graphicshistory">A Brief History and Summary of Graphics Programming</a>
          |                <br><i>
          |                    12:30 AM - 09 February, 2018
          |                </i>
          |                <ul>
          |                    <li>In college, nearly every project I did for class had some aspect of 3D graphics. But I always only learned what I needed. This post satisfies my curiosity to learn how it all works without any tunnel vision.</li>
          |                </ul>
          |            </li>
          |            <li>
          |                <a href="/blog/ontechnology">On Technology</a>
          |                <br><i>
          |                    8:30 PM - 08 November, 2017
          |                </i>
          |                <ul>
          |                    <li>Technological progress is simultaneously the driving force behind modern capitalism, and our only potential salvation from it. So how can we use it correctly? What do we need to change before we can begin work in earnest?</li>
          |                </ul>
          |            </li>
          |            <li>
          |                <a href="/blog/ontravel">On Travel</a>
          |                <br><i>
          |                    10:30 PM - 25 October, 2017
          |                </i>
          |                <ul>
          |                    <li>The pull towards adventure, excitement, and leisure. Is it worth it? How can I do it in a healthy and ethical way? Are good ethics even possible to achieve? Thoughts on these questions and more!</li>
          |                </ul>
          |            </li>
          |        </ul>
          |    </body>
          |</html>
          |""".stripMargin.replaceAll(" +", "")
    }

    "should display the 'On Travel' blog post" in {
      val controller = new BlogController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.onTravel().apply(FakeRequest())
      val bodyText: String = contentAsString(result)
      bodyText.replaceAll(" +", "") mustBe
        """<!DOCTYPE html>
          |
          |<html lang="en">
          |  <head>
          |    <meta charset="utf-8">
          |    <meta http-equiv="X-UA-Compatible" content="IE=edge">
          |    <meta name="viewport" content="width=device-width, initial-scale=1">
          |    <link rel="shortcut icon" href="#" />
          |    <title>Lipson</title>
          |  </head>
          |    <body>
          |        <h1>
          |            On Travel
          |        </h1>
          |        <p>&emsp;&emsp;I know a lot of people who love to travel. They claim that it makes them better people.
          |            Some go even farther and claim that it’s a necessity if you want to be a good person at all.
          |            They repeat the mantra of learning about other cultures, meeting new people, seeing new sights,
          |            experiencing the world, etc… as if they’re automatically better people by virtue of the fact
          |            that they’ve seen more things than others have seen. It takes an entirely over privileged and
          |            narcissistic person to embody the concept. Too often “experiencing new things” and “learning
          |            about cultures” means taking fun pictures to put online and eating food. It’s a petty act of
          |            consumption, exploitation even, that serves only to further one’s opinion of oneself. That’s
          |            not to say that travel’s necessarily bad either. It just, like with all things, matters how you
          |            do it, and how you think about how you do it. I too feel the pull towards adventure that comes
          |            with having your base needs met in life. So the question then becomes not ‘is traveling selfish
          |            and bad?’ but rather ‘how can I travel without doing it in a selfish and bad way?’</p>
          |        <p>&emsp;&emsp;A few friends of mine have stayed in various places in the world in a more long-term
          |            capacity. They spend time in school, staying with host families while taking on part-time jobs,
          |            working on farms, etc… This feels more reliably ethical to me. As a gut reaction, the work involved
          |            definitely contributes. The fun, short-term, exploitative romp around a foreign place while taking
          |            pictures and marveling at material culture like it’s some sort of circus show stands in stark
          |            contrast to these more contributory stays in that while you learn and grow as person from the
          |            culture you’re becoming integrated with, you also supply your labor and your perspective in return.
          |            More of a give-and-take relationship instead of a parasitic one. And yet still I feel my skin
          |            crawling when I think about myself actually engaging in one of these types of stays. Although
          |            you stay for longer and help more, I can’t shake the feeling that it’s still too temporary. How
          |            much can you really help by giving a few months of time to a farm or school? You might say that
          |            helping all that you can while growing as a person is worth it, that the cumulative good between
          |            the help you contribute and the perspective you gain (and can then share with others) makes it
          |            ethical. I might even agree with you (I’m deciding as I write this), but does that really make
          |            it worth it? Is traveling really the only way to gain this kind of perspective? We find ourselves
          |            at a dilemma of travel being the only means of self-actualization and ideological harmoniousness,
          |            so people who don’t have the opportunity or the money to travel can never achieve life on the
          |            highest plane of existence. I spit on that idea. While spending time in different parts of the
          |            world might be a good conduit for self growth, it’s not the only conduit. There are many others
          |            that you can do right at home on the cheap, some are even better. You can read books, watch movies,
          |            learn languages, etc… So why travel at all? Because it’s fun? You spend thousands of dollars
          |            that could’ve been used for a better purpose in order to get some sort of feeling of fulfillment
          |            that you could’ve gotten by checking out a few books from your local library? That seems selfish,
          |            and like I don’t want to do it.</p>
          |        <p>&emsp;&emsp;That feeling of “fulfillment” doesn’t really exist anyway, through travel or any other
          |            means. It’s just you telling yourself that you’ve escaped the oppressive global society in which
          |            we live, when in actuality you’ve only entrenched yourself further within that system by supporting
          |            global capitalism and engaging in multi-national commodity fetishism. The more I think about
          |            this the sadder I get because I don’t think achieving “self-actualization” is actually possible.
          |            I’m doomed to the world of cognitive ideological dissonance. Everything I touch, see, taste,
          |            hear, and smell can be traced back to something unethical. I bought some clothes from Walmart,
          |            which were made in sweatshops by exploited workers in Bangladesh. I bought some granola bars
          |            from Target, which contain palm oil, the main ingredient that gives motivation for the deforestation
          |            of the world’s rainforests. I bought some furniture at IKEA, a company that’s been linked to
          |            child labor, nazism, and environmental issues. I paid for a ride from Uber, thus supporting the
          |            ongoing sexual harassment, and underpaying workers that Uber engages in. You can look up any
          |            major brand in the world and find a host of ethical issues. Not to mention the underlying issues
          |            inherent with our economic system. You might think it’s more ethical to buy a used house than
          |            build a new one, for example, in order to reduce waste. And it seems at first glance that you’re
          |            right, certainly it’s the more ethical choice (though I’m sure whatever real estate agency sold
          |            you the house has their own skeletons in the closet), but it still has its problems. Why should
          |            you get that house while other people starve and die on the streets without homes? Because you
          |            worked to get the money to do so? Even if that were true, which in most cases it’s not because
          |            to even have the opportunity to work to get that money is a privilege (a lot of people have to
          |            work just to survive), it’d still make no sense. People who are lazy deserve to die? They should
          |            go hungry? Their kids too? The logic doesn’t follow, and it’s wrong on a base level anyway. (This
          |            example was about houses, but the same can be said about apartments, town homes, or any other
          |            living situation).</p>
          |        <p>&emsp;&emsp;There’s nothing you can do to escape it. We’re assaulted in every part of our lives by
          |            the capitalist ideology. When you’re on the bottom of the class system you have to fight just
          |            to survive and you’re forced to accept the fact that you’ll probably always be exploited. When
          |            you, like me, are on or near the top you either get to live with the terrible knowledge that
          |            no matter what you do you can’t help everyone and that an ethical world doesn’t exist, or you
          |            can ignore it and become a libertarian, conservative, moderate, or centrist (all terms for the
          |            same thing, in my view). Far too many people choose the latter. They engage their lives through
          |            the Marxist idea of “false consciousness”, which is a state of mind that doesn’t allow you to
          |            see the true nature of your own economic situation. It’s a hard realization to make, and a hard
          |            life to confront, but not nearly as hard as the lives of those below you. Never forget that.</p>
          |        <p>&emsp;&emsp;So the proposed solution of travel as a means of escape doesn’t work. It may even be
          |            a bad thing, because it makes you feel as though you’ve escaped when you haven’t, which causes
          |            complacency. To be fair though, nothing else works either. All that we can do is try our best
          |            to fight back. We’re doomed to failure but if enough of us try hard enough and consistently enough
          |            maybe someday we won’t fail. You can buy used clothes from the thrift store instead of new ones
          |            from Walmart; they may be traced back to the same types of sweatshops but at least it’s not contributing
          |            to their overall sales. You can avoid products with ingredients like palm oil that support environmental
          |            disaster. You can bike or take the bus instead of taking an Uber. etc. There must be a mode of
          |            vacation or traveling that’s equivalent to these alternatives, one that’s at least not worse
          |            than living in general. Again, as with most issues, it depends on a lot of different things.
          |            How are you living right now? How will you live when you travel? Why is one better than the other?
          |            I came across this website of two people traveling with their dogs in a camper long-term. It’s
          |            been a very helpful resource (I’m also a dog owner). But the more I read the more I realized
          |            they weren’t really living any differently than they otherwise would have. They had a super nice,
          |            new camper with a full furnishing of plates, silverware, cooking stuff, etc. It was like they
          |            picked up their bourgeoisie suburban home, put wheels under it, and went. Clearly in that case
          |            traveling wasn’t the right choice because it wasn’t a conduit for them to change and live more
          |            ethically. If you can’t justify your life then you need to change it, and you’ll never be able
          |            to justify your life under our current system, so you have to be in a constant state of change.
          |            (Side note, this is why I’m so horrified of staying in one place or one job for too long). So
          |            maybe the answer to the question of whether or not you should do something is the same as the
          |            answer to the question of whether or not that thing is something you’ve never done before. In
          |            other words, if you haven’t done it you should do it, within reason obviously, don’t go trying
          |            out murder because it’s a new experience. But with traveling, maybe the fact that it’s novel
          |            and will jar me out of where I’m at is justification enough. I know that the way that I live
          |            now isn’t as ethical as I’d like. I’m fairly certain that there exists no situation in which
          |            I can truly live better, but I won’t know unless I try something different. And at the very least
          |            I can incrementally change my life.</p>
          |        <p>&emsp;&emsp;Karl Marx summed up my parting thoughts well in his economic and political manuscripts
          |            of 1844 when he said, “The less you eat, drink, and buy books; the less you go to the theatre,
          |            the dance hall, the public house; the less you think, love, theorise, sing, paint, fence, etc.,
          |            the more you save - the greater becomes your treasure which neither moths nor rust will devour
          |            - your capital. The less you are, the less you express your own life, the more you have, i.e.,
          |            the greater is your alienated life, the greater is the store of your estranged being.” I refuse
          |            to become an empty shell of a person. I refuse to submit, conform, consume, and obey like the
          |            capitalistic society I’m entrenched within wants me to. I will express myself and my life in
          |            whatever way I see fit, and you should too. If that means you want to travel, then travel, but
          |            do it as ethically as you can, otherwise your fight won’t last very long. Buy used equipment,
          |            live off of what you need not what you want, absorb information from different perspectives,
          |            give information from your perspective. Most importantly, help others do the same. I’m
          |            tired of seeing instagram pictures of people’s trip to foreign countries when the
          |            pictures are flushed with white Americans. I don’t want to hear about how good the barbeque is
          |            in South Korea, or how crazy it is to see the Eiffel Tower in person. I want to hear about how
          |            you lived, what you learned, how you grew, and what you did to give back.</p>
          |        <h3>
          |            Sources
          |        </h3>
          |        <ul>
          |            <li>
          |                <a target="_blank" href="https://www.marxists.org/archive/marx/works/1844/manuscripts/needs.htm">
          |                    Karl Marx's Manuscripts of 1844.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="https://en.wikipedia.org/wiki/False_consciousness">
          |                    I don't care what your high school teacher said, Wikipedia is a good source.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="https://www.amazon.com/Phenomenology-Spirit-G-W-Hegel/dp/0198245971/ref=sr_1_1?s=books&ie=UTF8&qid=1508986667&sr=1-1&keywords=phenomenology+of+spirit">
          |                    Hegel's Phenomenology of Spirit.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="https://www.theguardian.com/business/2013/nov/18/walmart-bangladesh-factories-fail-safety-review">
          |                    Walmart is evil.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="http://www.ucsusa.org/global-warming/stop-deforestation/drivers-of-deforestation-2016-palm-oil#.WfFPsxNSxE4">
          |                    Palm oil is evil.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="http://www.hbs.edu/faculty/Pages/item.aspx?num=33278">
          |                    IKEA is evil.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="https://www.theguardian.com/technology/2017/may/23/uber-underpaid-drivers-new-york-city">
          |                    Uber is evil.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="http://watch.everythingisterrible.com/">
          |                    Everything is evil and terrible, including you, me, and everyone.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="https://weretherussos.com/full-time-rving-large-dogs/">
          |                    Look at these people. Terrible.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="https://www.your-rv-lifestyle.com/rving-with-a-dog/">
          |                    Absolutely abhorrent.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="https://www.tourismconcern.org.uk/ethical-travel-guide/">
          |                    But we can still try our best!
          |                </a>
          |            </li>
          |        </ul>
          |    </body>
          |</html>
          |""".stripMargin.replaceAll(" +", "")
    }

    "should display the 'On Technology' blog post" in {
      val controller = new BlogController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.onTechnology().apply(FakeRequest())
      val bodyText: String = contentAsString(result)
      bodyText.replaceAll(" +", "") mustBe
        """<!DOCTYPE html>
          |
          |<html lang="en">
          |    <head>
          |        <meta charset="utf-8">
          |        <meta http-equiv="X-UA-Compatible" content="IE=edge">
          |        <meta name="viewport" content="width=device-width, initial-scale=1">
          |        <link rel="shortcut icon" href="#" />
          |        <title>Lipson</title>
          |    </head>
          |    <body>
          |        <h1>
          |            On Technology
          |        </h1>
          |        <p>&emsp;&emsp;Never in the history of mankind have we been presented with such a clear path to salvation
          |            and perfection as modern technology. I mean, it’s simple right? We make things that make our
          |            lives easier until we reach a point where our lives no longer need to get easier... It’s turned
          |            out to be a lot more complicated than this. You may be familiar with the Accelerationist movement,
          |            made popular by Nick Land. It can be summed up as an ideology geared towards expanding the prevailing
          |            system of late-stage capitalism to its most extreme form in order to trigger radical social change
          |            and economic liberty. Two schools of thought, right-accelerationism and left-accelerationism,
          |            prevail amongst those who follow this technological mantra.
          |        </p>
          |        <p>&emsp;&emsp;Right-accelerationists believe that an anarcho-capitalist society would naturally
          |            benefit everyone. They want to tear down all barriers to free enterprise and technological expansion,
          |            including regulations, environmental sanctions, international trade rules, and often government
          |            itself. This may sound familiar as being basically the beliefs of the most extreme right wing
          |            American politicians (tea party members and Trump worshippers). It’s almost too easy to see the
          |            inherent flaws with this set of beliefs so I won’t spend much time on it. A fully and singularly
          |            profit-driven society leads only to the concentration of that wealth to a small percentage of
          |            the people, those who happen to be in a position where they own the mechanisms that create goods
          |            and services, or, in the context of our post-1990 late-stage capitalist hellscape, those who
          |            own and can operate technology. Without proper distribution of resources, inequality would run
          |            rampant. The vast majority of people would be homeless and jobless, forced to suffer under the
          |            rule of capitalist technocrats like Mark Zuckerberg, Elon Musk, and Jeff Bezos.
          |        </p>
          |        <p>&emsp;&emsp;Left-accelerationists are an odd bunch. The most radical among them want to purposely
          |            push the symptoms of late-stage capitalism farther because they know that capitalism has self-destructive
          |            tendencies and they want to accelerate the process of that self-destruction. In other words,
          |            the revolution can’t begin in earnest until the right-accelerationist dystopia has been realized.
          |            I definitely don’t agree, but this seems to me to be the most logical school of left-accelerationist
          |            thought; at least they recognize their own absurdity and realize that things are going to get
          |            much worse before they get better. More optimistic but at the same time more ignorant left-accelerationists
          |            think that eventually technology will accelerate past the horizon of capitalism and into the
          |            socialist utopia with little effort. They want to repurpose technology to achieve more socially
          |            constructive and environmentally conscious ends, as if the mechanisms capitalism uses to thrive
          |            could be separated from capitalism itself without fundamental structural reform. Elon Musk again
          |            comes to mind when criticizing this theory. His entire business model is proof that you can take
          |            this liberal pipe dream of technological salvation, and commodify it to sell it back to those
          |            same “progressives” for profit, thus driving the institution that your consumer base claims to
          |            hate.
          |        </p>
          |        <p>&emsp;&emsp;The accelerationist views lie in stark contrast to the much more common, blue-collar
          |            luddite view that technological innovation and automation are bad for society. Rich liberal elites
          |            just want to take people’s jobs away so they can make more money, and we should destroy the means
          |            of production (modern technology) before they have the chance. Touted by more moderate conservatives
          |            looking to score a quick win with middle and working class voters, this view holds us back from
          |            our potential salvation the most out of any discussed thus far. I wholeheartedly sympathize with
          |            the sentiment, but instead of blaming technological progress itself, we should instead blame
          |            the socio-economic processes that put us in a position where tools created for the betterment
          |            of modern life are used for exploitation.
          |        </p>
          |        <p>&emsp;&emsp;Why have these views popped up? Why are they all so wrong? It’s clear to me that capitalism
          |            and technology are deeply linked with one another, but what these positions seem to mistake is
          |            the direction they’re intertwined in. More specifically, they assume this connection is bidirectional,
          |            or that one can’t exist without the other. While capitalism certainly can’t exist without technological
          |            growth and innovation as its primary driving force, no real evidence exists to suggest that technology
          |            must be constrained to capitalism. That being said, with the comforts of technology in the modern
          |            world comes the civic duty to recognize that the modern technological revolution, and indeed
          |            all past technological revolutions (IE the industrial revolution), are centered not around reducing
          |            hours and making lives easier, but rather maximizing profit in the form of capital for those
          |            in control of the technology (this specifically is what most left-accelerationists miss). In
          |            our current system, machines and technology should only be viewed from the perspectives of the
          |            workers they replace, as material manifestations of the force of control exerted by those above
          |            them. This alienation is bad for society, despite what modern technologists might claim. Again
          |            I find myself turning to Karl Marx when searching for guidance. His words rang true when he said
          |            “The production of too many useful things results in too many useless people.” In order to realize
          |            the profound and lasting impact technology could have on our society, we need to reshape the
          |            mechanisms and systems which utilize it. A new regime must be introduced, one that works on entirely
          |            different principles than the one we have right now. One where technology can be pushed as far
          |            as possible, and be made as useful as possible, but people still have the means of survival that
          |            they need and are thus free to seek out whatever useful endeavor they choose. Our first priority
          |            must then be the implementation of this new regime, but what does that entail? The topic could
          |            be an entire blog post (or even life’s work) in and of itself, and has less to do with technology
          |            (the subject in question) directly, so I’ll glide past it for now. It definitely involves what
          |            the Brazilian people call ‘Renta básica’, or universal base income. And it definitely involves
          |            government funded food, water, and shelter for all people. Suffice to say, once a new institution
          |            is put in place we can then focus more heavily on pushing technological progress forward. To
          |            say that the most important thing to do right now is to tinker with computers for the fun of
          |            it while people starve and die on the streets is an abhorrent view.
          |        </p>
          |        <p>&emsp;&emsp;Assuming, though, that you have technological skill and are also on board with not letting
          |            people starve and die (as well as eliminating all the other atrocities under late-stage capitalism,
          |            which is too long of a list to go through), you don’t have to sit on your hands. You can still
          |            work to create innovative solutions, so long as the solutions you implement actually serve this
          |            greater goal. For example, you can create automated food delivery systems for those who don’t
          |            have enough to eat, you can start or contribute to technological education non-profit organizations,
          |            you can help to implement sustainable environmentally friendly systems through the use of technology.
          |            The list of truly productive ways a technologist can contribute is limited only by their imagination,
          |            and yet nowhere on that infinite list does it say “sit in a cube for 8 hours a day creating solutions
          |            designed to make whatever corporation you’re working for at the time money”. The goal of fundamental
          |            structural change must be the focus, with technology merely as one of its many drivers. Once
          |            we get enough technologists on board with this mode of thought, we can begin to create a two-pronged
          |            effort where we attack the problem from both sides. We’ll both be pushing for structural change
          |            on a fundamental political level, convincing those who disagree and working towards a socio-economic
          |            revolution, and we’ll be creating the solutions to be used by this new system behind dissenters’
          |            backs so that eventually we won’t need to convince them, they’ll be able to see how much better
          |            it is for themselves. The catch is that enough of us have to do it, and that we can’t get entrenched
          |            in the capitalist rat-race (which is all too easy to be trapped by). This is probably a pipe
          |            dream, but it’s one that I firmly believe in so I’m going to go ahead and keep smoking.
          |        </p>
          |        <h3>
          |            Sources
          |        </h3>
          |        <ul>
          |            <li>
          |                <a target="_blank" href="http://criticallegalthinking.com/2013/05/14/accelerate-manifesto-for-an-accelerationist-politics/">
          |                    The Accelerationist Manifesto.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="http://hyperstition.org/">
          |                    Hyperstition.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="https://pmacdougald.wordpress.com/2016/04/14/accelerationism-left-and-right/">
          |                    Accelerationism Left and Right.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="https://techcrunch.com/2017/03/26/technology-is-killing-jobs-and-only-technology-can-save-them/">
          |                    Technology is Killing Jobs and Only Technology Can Save Them.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="https://www.yahoo.com/news/brazilian-town-embraces-universal-income-experiment-102916533.html">
          |                    Renta Básica in Brazil.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="https://www.amazon.com/Technology-History-Dilemma-Technological-Determinism/dp/0262691671">
          |                    Does Technology Drive History? The Dilema of Technological Determinism.
          |                </a>
          |            </li>
          |        </ul>
          |    </body>
          |</html>
          |""".stripMargin.replaceAll(" +", "")
    }

    "should display the 'A Brief History and Summary of Graphics Programming' blog post" in {
      val controller = new BlogController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.graphicsHistory().apply(FakeRequest())
      val bodyText: String = contentAsString(result)
      bodyText.replaceAll(" +", "") mustBe
        """<!DOCTYPE html>
          |
          |<html lang="">
          |    <head>
          |        <meta charset="utf-8">
          |        <meta http-equiv="X-UA-Compatible" content="IE=edge">
          |        <meta name="viewport" content="width=device-width, initial-scale=1">
          |        <link rel="shortcut icon" href="#" />
          |        <title>Lipson</title>
          |    </head>
          |    <body>
          |        <h1>
          |            A Brief History and Summary of Graphics Programming
          |        </h1>
          |        <p>&emsp;&emsp;Let’s talk about the graphics pipeline, and specifically the parts of the graphics pipeline
          |            you can control through programming. It's essentially the process the computer takes to turn
          |            code into graphics displayed on the screen. Right now there are a lot of steps and sub steps within
          |            this pipeline, and we can control a decent number of them with the use of high level languages.
          |            To better understand where we’re at now it’s best to look at where we’ve come from. I’ll start
          |            with Pong, a 2D video game created in 1972 by Nolan Kay Bushnell.
          |        </p>
          |        <p>&emsp;&emsp;Back then computers were a lot less powerful, computing power was a huge constraint.
          |            When Bushnell first thought of making a computer arcade game, he wanted to make a game similar
          |            to Spacewar, something he played a lot and a game that was developed at MIT because they had
          |            the resources to do so. Bushnell tried to find a way to make a new game that was economical and
          |            could be sold to arcades. But he realized that no matter how many quarters he made off his game,
          |            it just wouldn’t cover the cost of the $4,000 “mini” computer (which was the size of a refrigerator,
          |            and was the cheapest on the market at the time) that was required to run it. Bushnell, ever persistent,
          |            tried to find elements of his game (like the stars in the background) that he could generate
          |            directly on the hardware of a chip rather than in the CPU of the computer. That’s when he had
          |            his big breakthrough. “Why not do it all on the hardware?” he asked himself. Instead of writing
          |            code to run on a processor, he designed switches on an actual circuit board to run the logic
          |            of the game and map the colors to the screen. This revolutionary idea allowed him to create the
          |            game for the measly cost of $1,000 and sparked his career. He’d later go on to found the company
          |            Atari, and create Pong using a similar strategy. He wrapped Pong’s circuit in one of the world’s
          |            first gaming consoles (it was solely dedicated to playing Pong), which originally sold to arcades
          |            for around $1,200 a pop, producing a nice profit. It became clear to the world that running graphics
          |            processing as close to the hardware was easily the most efficient way to do it. At this point
          |            the full graphics pipeline looked something like this.
          |        </p>
          |        <pre>
          | ____________
          ||            |
          ||   SCREEN   |
          ||            |
          | ‾‾‾‾‾‾‾‾‾‾‾‾
          |        </pre>
          |        <p>If it looks simple that's because it is. The hardware talks directly to the screen, there's nothing
          |            more to it.</p>
          |        </p>
          |        <p>&emsp;&emsp;Throughout the years this simple concept of running logic on hardware was utilized more
          |            in games, movies, and television. A particularly notable instance comes from Star Wars IV: A
          |            New Hope. Larry Cuba was tasked with implementing the animation for the death star. Before that
          |            the vast majority of graphics were done in 2D, using a cartesian coordinate system with an X
          |            and Y axis. Cuba created an application in the GRASS (GRAphics Symbiosis System) programming
          |            language that allowed for the input of a third Z axis. Not only that, but his application allowed
          |            for all the logic having to do with basic transformations, like rotation and scaling, as well
          |            as the displaying of the model to a 2D picture, to run directly on the hardware. It was wildly
          |            efficient, but Cuba hit a snag, which would end up exposing one of the more difficult problems
          |            in the history of computer graphics. The only reason the display was able to happen directly
          |            on the hardware was because it used parallel projection instead of true perspective, which essentially
          |            means that all the geometry of the scene was defined by rays being cast out from the perspective
          |            of the camera, and not as actual models in 3D space. Cuba stated about the problem, “...I needed
          |            perspective for this project, so I was back to using software for the projection and therefore
          |            was *not* able to animate the scene in real time.” In fact, moving the perspective logic back
          |            to the software made each frame get generated in around 12 hours and slowed his work down immensely.
          |            In the end, he was able to finish the death star animation in the nick of time despite the long
          |            processing time. We’ve now added a few steps in our graphics pipeline because of the constraints
          |            Cuba faced.
          |        </p>
          |        <pre>
          | ________________________________        ______________        ____________
          ||                                |      |              |      |            |
          ||   APPLICATION INITIALIZATION   | ===> |   GEOMETRY   | ===> |   SCREEN   |
          ||                                |      |              |      |            |
          | ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾        ‾‾‾‾‾‾‾‾‾‾‾‾‾‾        ‾‾‾‾‾‾‾‾‾‾‾‾
          |        </pre>
          |        <p>We have two steps that run as software before we're able to directly
          |            to the screen.</p>
          |        <p>&emsp;&emsp;Fast forward 5 years to 1982. 3D computer graphics evolved from being added flare in
          |            movies, to being what movies could be entirely comprised of. A film called Tron took the same
          |            CPU-computed vector graphics system that brought us the death star and expanded on it to create
          |            the world’s first movie with extensive 3D graphics. To achieve this, engineers working for Disney
          |            had to use elaborate matting strategies and complex sorting algorithms to create the illusion
          |            of a world made up of solid surfaces. The processing time was even longer for this movie because
          |            it had so much computation to do; it took up to 30 hours per frame.
          |        </p>
          |        <p>&emsp;&emsp;In the same year, a new concept was made popular that would rock the foundation of 3D
          |            graphics. The Commodore 64 had just come out, a personal computer that introduced rasterization.
          |            Rasterization allows you to represent images on your display in a grid system of rectangular
          |            points called pixels. The images are typically saved and parsed in a dot matrix structure, which
          |            is simply a two-dimensional patterned array. Dot matrix patterns have been used in knitting for
          |            centuries! Anyway, rasterization opened the door for film makers to innovate within the space
          |            of entertainment. Mapping pixels to colors allowed for an alternative to the vector graphics
          |            used to compute earlier models (like the death star or the models in Tron). The company Pixar
          |            used a combination of these strategies when creating their first ever animated short ‘Luxo Jr.’
          |            in 1985. They used the CPU to compute all of the geometry and effects of the scene, and then
          |            came up with algorithms to translate that geometry into a 2D pixel matrix format. This process
          |            would become known to the world as ‘rasterization’ or ‘rendering’, and Pixar would end up being
          |            pretty damn good at it. This puts another step in our graphics pipeline!
          |        </p>
          |        <pre>
          | ________________________________        ______________        ___________________        ____________
          ||                                |      |              |      |                   |      |            |
          ||   APPLICATION INITIALIZATION   | ===> |   GEOMETRY   | ===> |   RASTERIZATION   | ===> |   SCREEN   |
          ||                                |      |              |      |                   |      |            |
          | ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾        ‾‾‾‾‾‾‾‾‾‾‾‾‾‾        ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾        ‾‾‾‾‾‾‾‾‾‾‾‾
          |        </pre>
          |        <p>This is actually pretty much the same general pipeline we use today.
          |            All further innovation has been done within each of these boxes, specifically in getting portions of them to
          |            run closer to the hardware of the GPU.
          |        </p>
          |        <p>&emsp;&emsp;We didn’t really hit another high mark for 3D animation until the mid 1990s when larger
          |            film studios and design firms began to realize that they could really make a lot of money off
          |            of animated films and software products. In 1993 the first big budget CGI film, Jurassic Park,
          |            was released. Jurassic Park was special in that it utilized what’re called ‘animatronics’, which
          |            are lifelike 3D models made to scale and built using robotics to move in a particular way (in
          |            this case like how dinosaurs do). You film using these animatronics, and then layer your effects,
          |            like texturing, lighting, shading, etc… over it. This greatly reduced the amount of computation
          |            required because most of the geometry was taken care of by the animatrons.
          |        </p>
          |        <p>&emsp;&emsp;1994 marked another important year, when the Boeing 777 flight was tested by aerospace
          |            engineers entirely in Ansys, a 3D simulation software that allows you to upload models of whatever
          |            you want and define the parameters that constrain your test. This is merely one example of CAD
          |            (computer aided design) software changing the world of product design. It’d be way too long to
          |            go into detail about all examples.
          |        </p>
          |        <p>&emsp;&emsp;1995 was also a big year. You notice how these important years are getting closer and
          |            closer together? Just you wait. Another one of my favorite movies of all time, Toy Story, was
          |            released! It was a huge innovation because it was the world’s first fully CGI movie. More than
          |            that, it was the first instance of really intertwining CGI with storytelling. Everything from
          |            the storyboard through the script and the voice acting and the clay modeling and the sets and
          |            animations and effects served the final step of rendering. It was the first time where the entire
          |            narrative process had been wrapped within the context of 3D graphics. Needless to say, Toy Story
          |            was a huge hit and pushed Pixar forward as an industry leader within the space of entertainment.
          |        </p>
          |        <p>&emsp;&emsp;By 1997 interest had grown in 3D graphics to the point where everyday programmers wanted
          |            to be able to make their own graphics projects at home without requiring the big budget that
          |            comes along with being a movie studio or design firm. All of these algorithms and strategies
          |            for doing geometry, animation, effects, and rendering were great, but they were all wrapped in
          |            proprietary software applications that were owned by large companies. No one could touch them!
          |            A company called The Khronos Group was founded with the intent of giving developers an API-level
          |            interface to be able to make graphics software. They achieved their goal by releasing the first
          |            version of OpenGL. The graphics pipeline remained unaltered (for now), but it was now easy for
          |            a developer to simply import OpenGL into their C or C++ project and start making advanced 3D
          |            graphics. This was an absolutely huge event because it allowed for innovation to happen from
          |            the comfort of a single developer’s desk chair. As you can imagine, the world of graphics programming
          |            grew exponentially because of this accessibility.
          |        </p>
          |        <p>&emsp;&emsp;The early 2000s brought with them two drivers for innovation: the internet, and the video
          |            game industry. Mozilla, the open source software community founded by members of the Netscape
          |            company, was getting a lot traction with its rendering software. It was proof that the concepts
          |            of 3D animation could be applied within web pages. To the same end, a 3D design company named
          |            Sketchup released the first ever web-based CAD system. On the video game side, the Final Fantasy
          |            movie (entitled "The Spirits Within") became one of the first programs to animate tears, water
          |            and other liquids through the use of a system of small dots in 3D space called particles. The
          |            particles would have liquid-based physics applied to them, and then a reflective texture overlaid
          |            atop them. Also in the video game space, the DOOM3 graphics engine, in 2003, became the first
          |            high level program that would generate everything for you so you wouldn’t even have to worry
          |            very much about coding. Many video game startups and indie companies still use this same strategy
          |            with newer software programs (like Unity or Unreal Engine).
          |        </p>
          |        <p>&emsp;&emsp;In 2004 the Khronos group changed graphics programming again. They released a second
          |            version of OpenGL, known as OpenGL 2.0, which created a new high level language for running programs
          |            on the GPU instead of the CPU. This was a huge deal because of the parallelization that the GPU
          |            offers (<a href="https://www.youtube.com/watch?v=-P28LKWTzrI" target="_blank">here’s a fun demo showing this</a>).
          |            They called these programs shader programs, and the C-like language they created they called GLSL
          |            (Graphics Library Shader Language). Specifically they introduced two types of shaders, vertex shaders
          |            and fragment shaders. Vertex shaders are fed data about the specific vertices of the polygons through
          |            blocks in memory called vertex array objects. They take this info as input, run all their logic on the
          |            GPU, then output the new position of each vertex (there must be a 1-to-1 relationship between input
          |            vertices and output vertices). They typically perform geometric transformations like translation, scaling,
          |            and rotation (Though they can do a lot of other things too. For a good overview check out
          |            <a href="http://vertexshaderart.com/" target="_blank">this website</a>. An alternative to using vertex
          |            shaders is to use the CUDA platform, created by Nvidia in 2007. It allows you to write your GPU instructions
          |            in C++ instead of GLSL. It doesn’t particularly matter which you pick, they both do a good job, but
          |            typically programmers have an easier time understanding CUDA at first (I know I did) because the logic
          |            and the code look like what you’re used to. In the next phase, the fragment shader takes the concept of
          |            rasterization and pushes it onto the GPU. After the initial rasterization happens, the software will take the
          |            pixels and for each one generate a fragment, which is a block in memory that holds all of the
          |            data required about that pixel for any given frame. This data includes the X and Y coordinates,
          |            and all the interpolated per-vertex values from the vertex shader. These frames are used as input
          |            to the shader, then the fragment shader runs its logic on the GPU, and outputs a depth value
          |            and a color value associated with that pixel. Fragment shaders are typically used for lighting,
          |            texturing, and other effects (again can do a lot of other things.
          |            <a href="https://www.shadertoy.com/" target="_blank">Check it out!</a>). This is the first time in a long time
          |            that the graphics pipeline has been altered in a substantial way!
          |        </p>
          |        <pre>
          | ________________________________        _____________________        ___________________        ____________________        ___________________        _____________________        ____________
          ||                                |      |                     |      |                   |      |                    |      |                   |      |                     |      |            |
          ||   APPLICATION INITIALIZATION   | ===> |   INPUT ASSEMBLER   | ===> |   VERTEX SHADER   | ===> |   OTHER GEOMETRY   | ===> |   RASTERIZATION   | ===> |   FRAGMENT SHADER   | ===> |   SCREEN   |
          ||                                |      |                     |      |                   |      |                    |      |                   |      |                     |      |            |
          | ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾        ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾        ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾        ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾        ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾        ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾        ‾‾‾‾‾‾‾‾‾‾‾‾
          |        </pre>
          |        <p>&emsp;&emsp;Another five years later, in 2009, innovations in graphics programming were still in full
          |            swing. The Khronos group released OpenGL 3.0 which brought a new shader with it called the geometry
          |            shader. This allowed for all the geometry necessary for animations and whatnot to be run on the
          |            GPU. Along with that, other innovations were happening to combine graphics with real life models,
          |            building on what Jurassic Park had started. More advanced particle systems, image processing,
          |            and more were all happening too! Check out
          |            <a href="https://www.youtube.com/watch?v=qC5Y9W-E-po" target="_blank">this video</a> for a summary.
          |        </p>
          |        <pre>
          | ________________________________        _____________________        ___________________        _____________________        ___________________        _____________________        ____________
          ||                                |      |                     |      |                   |      |                     |      |                   |      |                     |      |            |
          ||   APPLICATION INITIALIZATION   | ===> |   INPUT ASSEMBLER   | ===> |   VERTEX SHADER   | ===> |   GEOMETRY SHADER   | ===> |   RASTERIZATION   | ===> |   FRAGMENT SHADER   | ===> |   SCREEN   |
          ||                                |      |                     |      |                   |      |                     |      |                   |      |                     |      |            |
          | ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾        ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾        ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾        ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾        ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾        ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾        ‾‾‾‾‾‾‾‾‾‾‾‾
          |        </pre>
          |        <p>
          |            The pipeline with the geometry shader step added.
          |        </p>
          |        <p>&emsp;&emsp;Following this work, the Khronos group introduced another shader in 2010 with the release
          |            of OpenGL 4.0. The line between the vertex shader and the geometry shader was taking too long.
          |            The process of dividing the vertices up into smaller primitives (called tessellation) to run
          |            geometric logic on was simply too costly to run on the CPU. So they made the tessellation shader.
          |        </p>
          |        <pre>
          | ________________________________        _____________________        ___________________        _________________________        _____________________        ___________________        _____________________        ____________
          ||                                |      |                     |      |                   |      |                         |      |                     |      |                   |      |                     |      |            |
          ||   APPLICATION INITIALIZATION   | ===> |   INPUT ASSEMBLER   | ===> |   VERTEX SHADER   | ===> |   TESSELLATION SHADER   | ===> |   GEOMETRY SHADER   | ===> |   RASTERIZATION   | ===> |   FRAGMENT SHADER   | ===> |   SCREEN   |
          ||                                |      |                     |      |                   |      |                         |      |                     |      |                   |      |                     |      |            |
          | ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾        ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾        ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾        ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾        ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾        ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾        ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾        ‾‾‾‾‾‾‾‾‾‾‾‾
          |        </pre>
          |        <p>&emsp;&emsp;A year later in 2011, Mozilla, in collaboration with the Khronos group, created WebGL,
          |            which is the same thing as OpenGL, but with a Javascript API instead of a C/C++ one. This meant
          |            that any graphics program you wanted to run you could now run in a browser using Javascript!
          |            Check out
          |            <a href="http://webglsamples.org/" target="_blank">this dope website</a> for some examples!
          |        </p>
          |        <p>&emsp;&emsp;Today the most innovative work in computer graphics is being done with AI, big data,
          |            source mapping, and parallelization. AIs are now able to recognize sketches drawn by humans, and generate plausible
          |            shapes for things like lamps or airplanes. Big data allows us to fully realize things like timelapse
          |            videos without having the background get jumpy or distorted. Source mapping can now manipulate
          |            videos in real time, altering the target video based on input from the source video
          |            (<a href="https://www.youtube.com/watch?v=ohmajJTcpNk" target="_blank">Here’s a fun example</a>). The most important
          |            innovation, however, has come from a push towards thread-based parallel programming. Some members of
          |            the Khronos group split to a new project called Vulkan, created in February of 2016, which is now
          |            the primary alternative to OpenGL. The main difference between the two is that Vulkan gives you lower level
          |            control over the task scheduling and threading that the operating system provides, and allows for the
          |            entire pipeline to be run in parallel with others on different threads.
          |        </p>
          |        <p class="centered">It’s an exciting time in the world of graphics programming!
          |            Who knows where we’ll go next! It's important that you know that I'm still learning this as I go,
          |            so if I got anything wrong in this post please email me and I'll fix it!
          |        </p>
          |        <h3>
          |            Sources
          |        </h3>
          |        <ul>
          |            <li>
          |                <a target="_blank" href="http://www.slate.com/articles/technology/technology/2014/10/the_invention_of_pong_how_nolan_bushnell_launched_the_video_game_industry.html">
          |                    The Birth of Pong.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="http://www.cs.cmu.edu/~ph/nyit/masson/history.htm">
          |                    History of Computer Graphics.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="http://www.commodore.ca/commodore-products/commodore-64-the-best-selling-computer-in-history/">
          |                    The Commodore 64.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="http://www.computerhistory.org/atchm/pixars-luxo-jr/">
          |                    Pixar's Luxo Jr.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="https://sciencebehindpixar.org/pipeline/animation">
          |                    Pixar's Animation Process.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="https://www.khronos.org/opengl/wiki/History_of_OpenGL">
          |                    History of OpenGL.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="https://www.mozilla.org/en-US/about/history/details/">
          |                    History of the Mozilla Project.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="https://www.khronos.org/webgl/wiki/Getting_Started">
          |                    WebGL Specification.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="https://www.siggraph.org/">
          |                    Siggraph.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="https://www.khronos.org/vulkan/">
          |                    Vulkan.
          |                </a>
          |            </li>
          |        </ul>
          |    </body>
          |</html>
          |""".stripMargin.replaceAll(" +", "")
    }

    "should display the 'On Sports' blog post" in {
      val controller = new BlogController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.onSports().apply(FakeRequest())
      val bodyText: String = contentAsString(result)
      bodyText.replaceAll(" +", "") mustBe
        """<!DOCTYPE html>
          |
          |<html lang="en">
          |    <head>
          |        <meta charset="utf-8">
          |        <meta http-equiv="X-UA-Compatible" content="IE=edge">
          |        <meta name="viewport" content="width=device-width, initial-scale=1">
          |        <link rel="shortcut icon" href="#" />
          |        <title>Lipson</title>
          |    </head>
          |    <body>
          |        <h1>
          |            On Sports
          |        </h1>
          |        <p>&emsp;&emsp;More often than not I find myself coming to the conclusion that, with regards to ethics,
          |            it matters less what you do, and more how you do it. Particularly how you think about whatever
          |            it is you’re doing. I know a lot of people who would say “but Ben, I’m a realist and a utilitarian!
          |            The only thing that matters is what you do and the subsequent series of events that follows as
          |            a result of the thing you’ve done!” These people disregard intentionality, which isn’t to be
          |            underestimated. But more importantly they disregard the most notable player in the inescapable,
          |            inexplicable, intoxicating inward journey that serves as a foundation for all ethical behavior:
          |            thought. Do you care enough
          |            to think really hard about what you’re doing, and why you’re doing it? Do you care enough to
          |            think really hard about those thoughts and what they mean? Do you care enough to attempt to draw
          |            conclusions from those thoughts and act accordingly? Utilitarian and consequentialist
          |            ethicists spend far too much time either wasting time over semantics
          |            that don’t actually matter, or rationalizing their behavior after the fact. They don’t care enough
          |            to think, and therefore miss the point entirely. You might say, in response to this claim, that,
          |            on the contrary, they think too much! They get into the weeds of the consequential results of
          |            their actions, before acting. This overly pragmatic analytic state of mind is not what I mean
          |            by “thinking”. Examining each branch in a tree of decision making as if it were some high school
          |            math problem does not suffice for summing up the ethical ethos that comes along with the human
          |            condition. The thoughts of men like Jeremy Bentham and John Stuart Mill are useful (one would
          |            hope utilitarianism would live up to its own name) in so far as they help guide very simple,
          |            day-to-day decision making, especially when it comes to the realms of enterprise and governing.
          |            And if you take their writing seriously and don’t misinterpret, it can be a good conduit for
          |            further thought. They’ve given what I find the be the only legitimate base for coming anywhere
          |            close to a quantifiable metric of morality, human suffering. But the utility that most people
          |            glean from their half-hour skim of utilitarianism ceases to be useful when confronted with any
          |            kind of complicated ethical question, which all ethical questions are if you care enough to think
          |            about them. Those that take utilitarianism as an end in itself may be able to make it through
          |            their life relatively happy, but they’ll never ascend to moral self-actualization. Self-actualization
          |            here described poorly (I can’t describe what I haven’t achieved) as becoming as ethical as one
          |            can possibly be. Only personal belief and thought can lead you to the peak of that mountain.
          |            Utilitarianism serves as an important stepping stone, a truly great foundation upon which to
          |            build your moral compass. But you have to actually believe it. As I stated, most who call themselves
          |            “utilitarians” don’t actually follow utilitarian ideals. Through story, metaphor, artistic expression,
          |            etc… we begin to grasp at what truly makes us human, and we begin to actually shape our ideology
          |            past the point of simple utilitarianism.
          |        </p>
          |        <p>&emsp;&emsp;Sports, especially modern sports, embody this concept particularly well. By and large
          |            the way we do sports in America exists as altogether overly extravagant and unwarranted. The
          |            tailgates, fireworks, marketing campaigns, etc… it’s all done with that typical grandiose American
          |            flare. And yet I still watch sports. What’s worse, I still enjoy watching sports. Despite the
          |            constant hate poured on them from members of my community (believe it or not computer science
          |            nerds who play Dungeons and Dragons aren’t huge on athletic competition), sports
          |            still speak to me. They may speak on a primal level of valuing strength, grit, and determination.
          |            Or perhaps on a level of sheer jealousy and outrage that I can’t do the physical feats I see
          |            professional athletes doing. They may just speak to me on a completely superficial level of enjoyment
          |            and escapism. Or all of the above. Whatever the case, at the end of the day I find myself enjoying
          |            sports more than I think I should.
          |        </p>
          |        <p>&emsp;&emsp;Which brings me to my big question: why shouldn’t I enjoy them? Because a bunch of nerds
          |            who I happen to call my friends tell me I shouldn’t? I find that answer insufficient. Though
          |            I trust their opinion, I’d still like to form my own. Perhaps because of the violence that they
          |            promote? Teaching our youth that it’s okay to hit each other as long as there’s a ball and some
          |            goal posts involved? But that too seems wrong. There are referees watching and enforcing fairness
          |            and sportsmanship. Any unwarranted act of violence gets called out. And besides, violence is
          |            a part of being a human. I don’t buy into the first-world idea of putting blinders on and pretending
          |            that violence isn’t happening or that it doesn’t exist or even that it shouldn’t exist. If anything,
          |            sports allow for us to express that which makes us human in a more restricted, civilized manner.
          |            Friedrich Nietzsche, in his book The Gay Science, said “Examine the life of the best and most
          |            productive men and nations, and ask yourselves whether a tree which is to grow proudly skywards
          |            can dispense with bad weather and storms. Whether misfortune and opposition, or every kind of
          |            hatred, jealousy, stubbornness, distrust, severity, greed, and violence do not belong to the
          |            favourable conditions without which a great growth even of virtue is hardly possible?”. There’s
          |            something wholly ugly about greatness. The miracles of human evolution and expansion can’t have
          |            possibly been realized without all of the “negative” aspects, including violence. The technology
          |            and other capitalist marvels that could serve to help a great number of people only exist because
          |            of it. So by allowing us to express these ugly aspects of greatness with fewer ramifications,
          |            sports help to smooth over and clean the rough, greasy, disgusting underside of our humanity
          |            while not hampering our growth. In fact, they may push growth even further. There are problems
          |            that come along with this growth and expansion, obviously. Terrible and sickening problems. Sports
          |            is an example of something that helps alleviate some of them.
          |        </p>
          |        <p>&emsp;&emsp;Another common complaint I hear about sports surrounds the “hero worship” of those with
          |            athletic ability. Some think that idolizing people who do nothing but play sports is unhealthy,
          |            and we should instead idolize based on the contents of people’s character. This allows sports
          |            stars to get away with rape, domestic abuse, child abuse, and murder, because people idolize
          |            them so much that in their minds sports stars couldn’t possibly do these horrible things. Not
          |            to mention all the money that the execs and CEOs of various sports franchises would lose if their
          |            players were actually held accountable for their actions. Maybe that’s why I shouldn’t enjoy
          |            sports. But I posit that worshipping work ethic is exactly what’s allowed for humanity to achieve
          |            anything at all, and that the work ethic demonstrated by professional athletes speaks to their
          |            character. Naturally I don’t condone the behavior of people like Ben Roethlisberger or Adrian
          |            Peterson; what they and many others have done is unforgivable and they absolutely should be brought
          |            to justice. That being said, the preferential treatment of certain people is a problem that’s
          |            inherent within our socio-economic system, not our games. Sports simply bring these symptoms
          |            of a larger problem to light. If sports were to exist in what many would consider a more ethical
          |            fashion, one where they were smaller, and no one idolized the players to the extent they do in
          |            the real world, then society would find a new group of people to idolize and to get off the hook
          |            whenever they did something wrong. There would be other Ben Roethlisbergers and Adrian Petersons
          |            out there in different arenas of life. You can’t blame the hero worship itself, you have to blame
          |            the container for it. Hero worship actually exists as a really positive influence on individual
          |            lives. I’m a big Denver Broncos fan, so obviously whenever I think about hero worship I think
          |            about Von Miller. When I see Von Miller I don’t see a man, I see a machine. One that shows up
          |            every day, does the work, puts in the time, pushes his body to its absolute limits despite the
          |            temporary pain it might cause him. It’s inspiring. Seeing this person who’s become an absolute
          |            wrecking machine awakens something within me, and, I assume, with the rest of his captive audience.
          |            It shows that through sheer power of will we can become greater than anything we’d ever thought
          |            possible before. Take that and extrapolate it to any other aspect of life and you see the appeal.
          |            We control our own destiny, our lives are in our hands. To me that’s beautiful. So what, then,
          |            is the real problem here? Why do I still feel itchy about sports and competition?
          |        </p>
          |        <p>&emsp;&emsp;Like I said the real problems with sports in America have less to do with sports themselves
          |            and more to do with all the other problems surrounding the sports. Thinking of sports in their
          |            “pure” unmarketed form, like handball as it existed in ancient Rome or something similar, doesn’t
          |            evoke the same discomfort that thinking of modern sports does. In popularizing sports in America
          |            we’ve allowed sports to become a conduit for a lot of deeper societal problems. This expands
          |            to a lot more problems than just hero worship and violence. One that pops into my mind has to
          |            do with capitalism, and income inequality. The professionals who play sports get paid way too
          |            much. On average, professional basketball players in the NBA make $5.15 million per year, baseball
          |            players in the MLB make $3.2 million, hockey players in the NHL make $2.4 million, and football
          |            players in the NFL make $1.9 million. Besides the fact that it’s backwards and the two coolest
          |            sports (hockey and football) are at the lower end of the spectrum, all the numbers are way too
          |            high. Humans legitimately just don’t need to make that much in any context. One of the fundamental
          |            concepts of my ethical belief system is to each according to their need. Unfortunately the free
          |            market allows for some to get far more than they need while others starve and die. We’ve solidified
          |            sports as a fundamental keystone in our capitalist economic system, and intertwining the two
          |            produces disastrous consequences on a daily basis. The competition sports offers has become indistinguishable
          |            from American capitalist competition, an embodiment of social darwinism. The two aren’t necessarily
          |            mutually inclusive, but in the case of modern society they correlate. The olympics, as an example,
          |            began as a phenomenon based around the essence of the bestial nature of being human, reducing
          |            society as a whole to an organized and civilized menagerie. That beautiful, “primitive” marvel
          |            of human spirit and grit transformed under capitalism into an unending quantifiable measurement
          |            of individual progress. Why do you think sports commentators are constantly rattling off statistics
          |            for individual players? It’s no longer about embracing what’s human within us at a base level,
          |            now it’s about the elimination of opponents through victory achieved by a markedly better result.
          |            Tack on the marketing, exploitation, and inequality that comes inherent in capitalism and you’ve
          |            got yourself a truly horrifying reality. The olympics now are a blight upon whatever city is
          |            unlucky enough to have the “honor” of hosting them. Cities spend huge amounts of money making
          |            themselves nice enough to be able to host, so much that they couldn’t possibly break even from
          |            the games themselves. Instead they’re left with the unused fancy new golf courses, stadiums,
          |            and soccer pitches that they built, costing them millions of dollars and returning nothing back
          |            to the community. Rio De Janeiro, the host of the 2016 olympic games, is projected to be paying
          |            off its debt for the next two decades at least. Meanwhile the local civilians of these cities,
          |            many of whom are already not very well off, struggle even more than they otherwise would have.
          |            All so America, China, Russia, and other superpower nations can carry home more shiny necklaces.
          |            It’s sickening, there’s no getting around it. As long as capitalism, particularly global capitalism,
          |            exists sports will be a struggle for survival for the poorer nations and athletes, and a struggle
          |            for dominance for the richer nations and athletes. The purpose is no longer the preservation
          |            of the human spirit through grit and determination, as would’ve been the case with wrestlers,
          |            swimmers, runners, weightlifters, etc... in Ancient Sumer, Egypt, Greece, and even Rome (though
          |            that’s where it began its descent with the advent of chariot racing and gladiator battles that
          |            often involved slavery). It’s now the preservation of the ruling order through measurable domination.
          |        </p>
          |        <p>&emsp;&emsp;Further proof of the ethical abasement of sports and athletic competition lies within
          |            the education systems in this country. Examining amateur, or pre-professional as I like to call
          |            them, sports within our schools reveals that we can draw the similar conclusions surrounding
          |            capitalism and profit-driven enterprise as the main negative influence. Sports-oriented universities,
          |            colloquially referred to as the “Big 10” schools, rake in cash on the backs of the labor of their
          |            student athletes. They pulled in $315 million in 2012 from sporting events alone, which excludes
          |            merchandise sales and god knows what else. The students who actually do the work, put in the
          |            time, and make themselves eligible candidates for the hero worship I was talking about earlier
          |            get paid nothing. How is it that they get paid nothing when professional athletes can be so grossly
          |            overpaid? It’s obvious to me that were the owners of professional sports franchises given the
          |            opportunity, they would undoubtedly be happy to pay their employees nothing. The owners of colleges
          |            are no different, they’ve simply found a better way to game the system. All they have to do is
          |            wrap a school around their disgusting business and suddenly they’re free to exploit the labor
          |            of the athletes while consuming all of the surplus value for themselves. Sure the athletes may
          |            get reimbursement in the form of education (only about 70% of the students do), but that seems
          |            to me like further means of entrapment. How could they possibly keep that system going? Wouldn’t
          |            people see through the facade? No! Because again, sports are ingrained within our socio-economic
          |            system so far that they’ve wedged themselves into our ethical system as well. We brainwash children
          |            from a young age and force them to think that sports are everything, sports are what you should
          |            strive towards, sports are what you should be doing after school, all day, every day. It doesn’t
          |            take long for it to become an activity that they do in school, for the school. The transition
          |            is so fluid that they don’t even realize they’re doing it for free. It’s a strange case of Stockholm
          |            syndrome where the captives don’t even know that they’re captives. We build our entire social
          |            hierarchy around this abhorrent reality and normalize the exploitation at the expense of those
          |            doing the work so that a few higher-ups within the business of college can continue to line their
          |            pockets with the blood, sweat, and tears of the students they claim to be helping. For sports
          |            to even flirt with ethics, they need to be eliminated from education. We have to make them
          |            extra-curricular, divorce them from schools, and actually pay the people who are providing the
          |            value.
          |        </p>
          |        <p>&emsp;&emsp;Besides education other problematic aspects prevail within modern sports surrounding
          |            rivalries and the strange and unhealthy dynamic they provoke. If you’ve ever seen a Boston Red
          |            Sox fan talk about people from New York you know what I’m talking about. People make large sweeping
          |            judgment about entire regions, even within the same country, without knowing anything about the
          |            people or culture from those regions, and immediately cast judgment upon them. When fist fights
          |            break out simply because one person supports Team A and another supports Team B, when it gets
          |            ingrained from a young age that people from your rival city are bad no matter what, that’s when
          |            you know there’s something wrong. But even past the sheer physical violence of it all, there’s
          |            something more. A strange sub-nationalistic ethos that promotes a “we can do no wrong” mentality.
          |            Implemented in large part by the owners and organizers of the various sports associations and
          |            franchises, rivalries primarily exist again as a way to line the pockets of the proverbial bourgeoisie.
          |            They pit fans against one another, fostering violence and unhealthy attitudes, so that people
          |            will spend more money on tickets, jerseys, stadiums, and whatever else. It’s all just a way of
          |            expanding the business. And sure, the excitement that rivalries bring to sports can help make
          |            it more entertaining for the viewer. It could be argued that the surplus value sports
          |            generates is a direct result of it being a more compelling product. But is it really worth it?
          |            For the hatred of violence, for the uncanny pack mentality, for the cult-like brainwashing that
          |            occurs? To me, it can’t be worth it, not so long as sports exist as a giant corporate money-making
          |            machine. The worst aspect is that rivalries, being inherently exiting, tend to attract people
          |            who otherwise wouldn’t get excited about athletics for any reason. They could’ve been introduced
          |            to sports as a way of expressing their humanity, instead they see it only as a way to hate on
          |            other states, cities, and towns.
          |        </p>
          |        <p>&emsp;&emsp;So while some might argue that sports in America have a net positive influence on the
          |            world through the excitement and entertainment they provide, and others might argue that sports
          |            in America have a net negative influence through the violence and hero worship they promote,
          |            there actually exists a much more prominent ethical culture surrounding athletic competition.
          |            One that’s based in raw traditionalist capitalist profiteering. My hope is that you, reader,
          |            will look past the surface level of ethical dialect and instead see the culture, expression,
          |            and environment that sports occupy. I don’t hate sports, if you got that idea from reading this
          |            post then you need to go back and read it again. On the contrary I love athletics. I encourage
          |            all to forever expel the idea of sports fans as being sloppy, violent people, and sports as being
          |            a conduit for the moral degradation of society. On the contrary, society is a conduit for the
          |            moral degradation of sports.
          |        </p>
          |        <h3>
          |            Sources
          |        </h3>
          |        <ul>
          |            <li>
          |                <a target="_blank" href="https://www.utilitarianism.com/utilitarianism.html">
          |                    An Overview of Utilitarianism.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="http://bleacherreport.com/articles/367924-violence-and-aggression-in-sports-an-in-depth-look-part-one">
          |                    Violence and Aggression in Sports: An In-Depth Look.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="https://www.amazon.com/Nietzsche-Science-Appendix-Cambridge-Philosophy/dp/0521631599">
          |                    The Gay Science.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="http://www.sportingnews.com/other-sports/news/are-professional-athletes-worthy-of-the-hero-worship-they-receive/mwq5kurpt8y314lj6veu9a0cd">
          |                    Are Professional Athletes Worthy of the Hero Worship They Receive?
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="https://www.mensjournal.com/sports/linebacker-training-von-millers-high-intensity-football-workout/">
          |                    Linebacker Training: Von Miller's High-Intensity Football Workout.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="https://www.huffingtonpost.com/visualnewscom/visualizing-the-yearly-sa_b_4184716.html">
          |                    Visualizing the Yearly Salary of Professional Athletes.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="https://www.usatoday.com/story/sports/olympics/2017/07/05/rio-olympics-look-to-ioc-for-help-with-40-million-debt/103439402/">
          |                    Rio Olympics Organizers Seeking IOC Help With Debt.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="https://www.psychologytoday.com/us/blog/promoting-empathy-your-teen/201109/why-sports-programs-dont-belong-in-high-schools-and-colleges">
          |                    Why Sports Programs Don't Belong in High Schools and Colleges.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="http://www.businessinsider.com/ncaa-schools-college-sports-revenue-2016-10">
          |                    NCAA College Sports Revenue.
          |                </a>
          |            </li>
          |            <li>
          |                <a target="_blank" href="https://robertparness.wordpress.com/2012/11/02/rivalries-in-sports-bring-passion-problems/">
          |                    Rivalries in Sports Bring Passion Problems.
          |                </a>
          |            </li>
          |        </ul>
          |    </body>
          |</html>
          |""".stripMargin.replaceAll(" +", "")
    }
  }
}
