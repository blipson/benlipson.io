package controllers

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
          |    <head>
          |        <meta charset="utf-8">
          |        <meta http-equiv="X-UA-Compatible" content="IE=edge">
          |        <meta name="viewport" content="width=device-width, initial-scale=1">
          |        <link rel="shortcut icon" href="#" />
          |        <title>Lipson</title>
          |    </head>
          |    <body>
          |        <h1>Blog</h1>
          |        <ul>
          |            <li>
          |                <a href="/blog/withandunion">Using WITH and UNION to optimize SQL queries</a>
          |                <br>
          |                <i>
          |                04:00 PM - 2 August, 2022
          |                </i>
          |            </li>
          |            <li>
          |                <a href="/blog/renderer/drawinglines">Building a 3D Renderer From Scratch, Part 2: Drawing Lines</a>
          |                <br>
          |                <i>
          |                07:45 PM - 24 February, 2020
          |                </i>
          |            </li>
          |            <li>
          |                <a href="/blog/renderer/tgaimages">Building a 3D Renderer From Scratch, Part 1: TGA Images</a>
          |                <br>
          |                <i>
          |                04:30 PM - 19 February, 2020
          |                </i>
          |            </li>
          |            <li>
          |                <a href="/blog/graphicsnotes">Graphics Programming Notes</a>
          |                <br>
          |                <i>
          |                04:30 PM - 06 November, 2019
          |                </i>
          |                <ul>
          |                    <li>Every few months I spend about a week diving into graphics programming stuff for fun. The problem: each time I've forgotten so much about it that I have to relearn a lot of what I previously already looked at. While each subsequent time is getting faster and faster, it's still rather annoying. So I've compiled this list of things that I seem to have to remind myself of every time in the hopes that it'll go faster in the future.</li>
          |                </ul>
          |            </li>
          |            <li>
          |                <a href="/blog/ongod">On God</a>
          |                <br>
          |                <i>
          |                02:30 AM - 30 July, 2019
          |                </i>
          |                <ul>
          |                    <li>I've spent a lot of time on this one.</li>
          |                </ul>
          |            </li>
          |            <li>
          |                <a href="/blog/functionalenlightenment">I Pushed My Feeble Imperative Consciousness to a New Plane of Existence and So Can You</a>
          |                <br>
          |                <i>
          |                    12:30 AM - 15 December, 2018
          |                </i>
          |                <ul>
          |                    <li>The internet is just tubes, our brains are disjoint, consciousness and substance are derived from monadic structures, and infinite break points.</li>
          |                </ul>
          |            </li>
          |
          |
          |
          |
          |
          |
          |
          |
          |
          |
          |            <li>
          |                <a href="/blog/graphicshistory">A Brief History and Summary of Graphics Programming</a>
          |                <br>
          |                <i>
          |                    12:30 AM - 09 February, 2018
          |                </i>
          |                <ul>
          |                    <li>In college, nearly every project I did for class had some aspect of 3D graphics. But I always only learned what I needed. This post satisfies my curiosity to learn how it all works without any tunnel vision.</li>
          |                </ul>
          |            </li>
          |
          |
          |
          |
          |
          |
          |
          |
          |
          |
          |
          |
          |
          |
          |
          |
          |
          |
          |
          |
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

    "should display the 'WITH and UNION' blog post" in {
      val controller = new BlogController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.withAndUnion().apply(FakeRequest())
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
          |            Using WITH and UNION to optimize SQL queries
          |        </h1>
          |        <p>&emsp;&emsp;I recently learned about this trick for optimizing certain SQL queries, so I thought I'd share it in a blog post.
          |            It applies specifically to any query in which you have to retrieve some data from a table via two different routes.
          |            What do I mean by routes? Well if we think of our database as a large graph, where the nodes are the tables and the
          |            edges are the foreign key relationships between them, then having a "route" to certain data is just a path between
          |            two nodes. So having two different routes to get at the data you want might look something like this:
          |        </p>
          |        <p>
          |            <img src="/assets/images/empty_graph.png" width="250" alt="" />
          |        </p>
          |        <p>&emsp;&emsp;Normally this would be done using
          |            <code>JOIN</code> clauses. To imagine that let's create an example schema. Perhaps you work at a company which uses
          |            a SQL database to manage its orders. Whenever a customer places an order it gets recorded within a table called <code>orders</code>
          |            . In addition to tracking orders, perhaps your company wants to track which customers placed which orders by name. So we have another
          |            table called <code>customer</code>. Let's say that a group of customers can all place a single order together, and that one customer
          |            can place many orders. As such, there must be a many-to-many relationship with a table between them, which we'll call
          |            <code>customer_order</code>.
          |        </p>
          |        <pre style="display: flex">
          |            <code>
          |                CREATE TABLE orders(
          |                    order_id SERIAL PRIMARY KEY,
          |                );
          |
          |                CREATE TABLE customer (
          |                    customer_id SERIAL PRIMARY KEY,
          |                    name VARCHAR
          |                );
          |
          |                CREATE TABLE customer_order (
          |                    customer_id INTEGER NOT NULL,
          |                    order_id INTEGER NOT NULL,
          |                    CONSTRAINT fk_customer FOREIGN KEY(customer_id) REFERENCES customer(customer_id),
          |                    CONSTRAINT fk_order FOREIGN KEY(order_id) REFERENCES orders(order_id)
          |                );
          |            </code>
          |            <img src="/assets/images/customer_to_order.png" style="padding-left: 5%" alt="" />
          |        </pre>
          |        <p>&emsp;&emsp;Now let's say that you're designing a feature for your system in which you need to search for all the orders that a customer
          |            has placed, and that you need to be able to search by the customer's name. It's pretty easy to implement this.
          |        </p>
          |        <pre>
          |            <code>
          |                SELECT orders.order_id
          |                FROM orders
          |                JOIN customer_order ON orders.order_id = customer_order.order_id
          |                JOIN customer ON customer_order.customer_id = customer.customer_id
          |                WHERE customer.name = ?;
          |            </code>
          |        </pre>
          |        <p>&emsp;&emsp;That's all well and good. But now let's imagine that you want to start grouping customers. It might be interesting to track
          |            which company a customer belongs to, if they belong to any at all. So you add another table called <code>company</code> with
          |            a one-to-many relationship with customers, assuming each customer can only belong to one company. But here's the kicker...
          |            let's say when someone places an order they can choose to either do it as an individual customer, or do it on behalf of
          |            a company. If they choose the latter, then they don't have to supply any of their own individual customer information
          |            because all of that information is already stored when the company "registered" in our system and assigned a few
          |            "point of contact" customers to themselves ahead of time. This means that in order to continue accurately tracking which
          |            customers are associated with orders, we need to link the <code>company</code> and <code>order</code> tables. Like the
          |            relationship between customers and orders, this will also have a many-to-many relationship with an intermediary table called
          |            <code>company_order</code>. Let's see what our new schema looks like.</p>
          |        <pre style="display: flex">
          |            <code>
          |                CREATE TABLE orders(
          |                    order_id SERIAL PRIMARY KEY,
          |                );
          |
          |                CREATE TABLE company (
          |                    company_id SERIAL PRIMARY KEY,
          |                )
          |
          |                CREATE TABLE customer (
          |                    customer_id SERIAL PRIMARY KEY
          |                    name VARCHAR,
          |                    company_id INTEGER,
          |                    CONSTRAINT fk_company FOREIGN KEY(company_id) REFERENCES company(company_id)
          |                );
          |
          |                CREATE TABLE company_order (
          |                    company_id INTEGER NOT NULL,
          |                    order_id INTEGER NOT NULL,
          |                    CONSTRAINT fk_company FOREIGN KEY(company_id) REFERENCES company(company_id),
          |                    CONSTRAINT fk_order FOREIGN KEY(order_id) REFERENCES orders(order_id)
          |                );
          |
          |                CREATE TABLE customer_order (
          |                    customer_id INTEGER NOT NULL,
          |                    order_id INTEGER NOT NULL,
          |                    CONSTRAINT fk_customer FOREIGN KEY(customer_id) REFERENCES customer(customer_id),
          |                    CONSTRAINT fk_order FOREIGN KEY(order_id) REFERENCES orders(order_id)
          |                );
          |            </code>
          |            <img src="/assets/images/company_to_order.png" style="padding-left: 5%" alt="" />
          |        </pre>
          |        <p>&emsp;&emsp;Going back to our feature, the query to retrieve all orders a customer has placed gets a little more complicated now. We
          |            need to account for the 2nd path where they may have placed it on behalf of a company.</p>
          |        <pre>
          |            <code>
          |                SELECT orders.order_id
          |                FROM orders
          |                JOIN customer_order ON orders.order_id = customer_order.order_id
          |                JOIN customer AS c1 ON customer_order.customer_id = customer.customer_id
          |                JOIN company_order ON orders.order_id = company_order.order_id
          |                JOIN company ON company_order.company_id = company.company_id
          |                JOIN customer AS c2 ON company.company_id = customer.company_id
          |                WHERE c1.name = ? OR c2.name = ?;
          |            </code>
          |        </pre>
          |        <p>&emsp;&emsp;That's a lot of <code>JOIN</code>s, each of which has bad performance implications as we traverse the table. Not to mention, we actually
          |            have to traverse through the <code>customer</code> table twice! You notice that there's an alias each time, either c1 or c2. That's because
          |            if we didn't alias them, then in the <code>WHERE</code> clause the database would have no idea which instance of <code>customer</code> we're talking about. Are we
          |            talking about the <code>customer</code> table we <code>JOIN</code>ed on when customers ordered directly, or the <code>customer</code> table we <code>JOIN</code>ed on when customers
          |            ordered through companies? It'd throw an "ambiguous column name" error. c1 and c2 allow us to specify the two routes to the
          |            <code>customer</code> table with different aliases. This means they both have to be in the <code>WHERE</code> clause if we want to make sure we're
          |            getting all orders associated with a given customer, because it's possible that customer could make orders both individually and
          |            on behalf of a company.
          |            <br>
          |            <br>
          |            &emsp;&emsp;Alright so it's not the most efficient solution, but is it really that bad? Yes. Primarily because it doesn't scale. Let's say
          |            we added a third route through which customers could place orders, say on behalf of some entity that we want to track separately
          |            from companies, like a government of a foreign country. We'd then have a <code>government</code> table and a <code>government_orders</code> table and in our
          |            query we'd then traverse through the <code>customer</code> table 3 times. Our <code>WHERE</code> clause would say <code>WHERE c1.name = ? OR c2.name = ? OR c3.name = ?"</code>.
          |            Now imagine we add 5 more routes to get to the <code>customer</code> table from the <code>orders</code> table. Our query would be a massive mess of <code>JOIN</code>s, we'd
          |            have to traverse the <code>customer</code> table N times, and our <code>WHERE</code> clause would be utterly unreadable. Wouldn't it be great if we could
          |            just traverse <code>customer</code> once and search by the customer's name once?
          |            <br>
          |            <br>
          |            &emsp;&emsp;Let's flip this problem on its head by starting at the end. Instead of getting all the orders
          |            for a given customer, let's first get the given customer and then get all the orders for that customer. Step one, getting the
          |            customer, is easy.
          |        </p>
          |        <pre>
          |            <code>
          |                SELECT customer_id, company_id FROM customer WHERE customer.name = ?;
          |            </code>
          |        </pre>
          |        <p>&emsp;&emsp;Ok great, now that we have the customer let's get all orders associated with that customer using the output of that first query.</p>
          |        <pre>
          |            <code>
          |                SELECT orders.order_id
          |                FROM orders
          |                JOIN customer_order ON orders.order_id = customer_order.order_id
          |                WHERE customer_order.customer_id = ?; -- the ? will be populated by the id returned from the previous query.
          |            </code>
          |        </pre>
          |        <p>&emsp;&emsp;You may have noticed we can actually optimize this little query and remove the <code>JOIN</code> because both the <code>orders</code> table and the
          |            <code>customer_order</code> table have the field <code>order_id</code>. So instead of searching for the orders from the <code>orders</code> table, we can just use <code>customer_order</code>.
          |        </p>
          |        <pre>
          |            <code>
          |                SELECT customer_order.order_id
          |                FROM customer_order
          |                WHERE customer_order.customer_id = ?; -- the ? will be populated by the id returned from the previous query.
          |            </code>
          |        </pre>
          |        <p>&emsp;&emsp;Ok that's great. We've optimized the query to not have to <code>JOIN</code> with the <code>customer</code> or <code>orders</code> tables at all. However, we've split it out
          |            into two different queries, which means we'd have to write some logic in code to stitch them together. Logic that could break or cause
          |            bugs. Wouldn't it be nice if we could do both queries together? Enter the <code>WITH</code> clause!
          |        </p>
          |        <pre>
          |            <code>
          |                WITH customer_query AS (SELECT customer_id, company_id FROM customer WHERE customer.name = ?)
          |                SELECT customer_order.order_id
          |                FROM customer_order
          |                JOIN customer_query ON customer_query.customer_id = customer_order.customer_id
          |                WHERE customer_order.customer_id = customer_query.customer_id;
          |            </code>
          |        </pre>
          |        <p>&emsp;&emsp;At first glance it may seem like we're still <code>JOIN</code>ing on the <code>customer</code> table. After all, we're doing a <code>JOIN</code> on the <code>customer_id</code>. But if
          |            you look closely you'll see that the <code>JOIN</code> actually happens on the output of the <code>WITH</code> clause, labeled as <code>customer_query</code>. This means that
          |            it's only <code>JOIN</code>ing on the specific subset of data that we got when we did the first query inside the <code>WITH</code>. Instead of having to traverse
          |            the entire <code>customer</code> table, it only needs to look at one row! Yes it's a <code>JOIN</code>, but a <code>JOIN</code> on a set of data with only one row barely has
          |            an effect on performance at all.
          |            <br>
          |            <br>
          |            &emsp;&emsp;Awesome, but what about the other path through the data? With what we have now we can get all orders placed directly by a customer
          |            blazingly fast, but we still need to also get all the orders placed on behalf of a company. How can we possibly do that in the same query?
          |            Enter the <code>UNION</code> clause!
          |        </p>
          |        <pre>
          |            <code>
          |                WITH customer_query AS (SELECT customer_id, company_id FROM customer WHERE customer.name = ?)
          |                SELECT customer_order.order_id
          |                FROM customer_order
          |                JOIN customer_query ON customer_query.customer_id = customer_order.customer_id
          |                WHERE customer_order.customer_id = customer_query.customer_id
          |                UNION
          |                SELECT company_order.order_id
          |                FROM company_order
          |                JOIN customer_query ON customer_query.company_id = company_order.company_id
          |                WHERE company_order.company_id = customer_query.company_id;
          |            </code>
          |        </pre>
          |        <p>&emsp;&emsp;Note that we now <code>JOIN</code> on the same single-line subset of the <code>customer</code> table defined in our <code>WITH</code> clause as <code>customer_query</code>, but this time
          |            we're joining by the <code>company_id</code>. Also note that we did the same performance optimization to get the <code>order_id</code> from <code>company_order</code> rather
          |            than having to <code>JOIN</code> on the <code>orders</code> table. Ta-da! Now we have all the data we need, we didn't have to <code>JOIN</code> on <code>orders</code> or on the full <code>customer</code>
          |            table at all, and our query will scale as we add more and more paths through our database.
          |            <br>
          |            <br>
          |            &emsp;&emsp;That's all I've got for this post! I just thought it was a cool optimization, so I decided to share it. If you're wondering why I learned
          |             about this, I work as an engineer on the promotions team at Target. Any time there's a sale anywhere at Target, online or in store,
          |            my team facilitated that. There was a need for people to be able to search for all the promotions that affected a given item. The problem
          |            is that an item can be added in many distinct ways to an offer. It can be added directly by id by saying "this item is on this offer".
          |            It can be added through the department by saying "this item is in the menswear department and the menswear department is on this offer".
          |            It can be added through the brand by saying "this item is a part of the Levi's brand and the Levi's brand is on this offer". I won't bore
          |            you too much, but there's 6 or 7 different paths through the data, and some of them could be combined (I.E. "this item is a part of menswear
          |            and the Levi's brand and menswear belonging to the Levi's brand is part of this offer"). Suffice to say, doing a bunch of JOINs and having
          |            a bunch of ORs in my WHERE clause to check the same thing wasn't going to cut it performance-wise, so I was forced to research this solution.
          |        </p>
          |    </body>
          |</html>""".stripMargin.replaceAll(" +", "")
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
          |        <p>It’s an exciting time in the world of graphics programming!
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

    "should display the 'I Pushed My Feeble Imperative Consciousness to a New Plane of Existence and So Can You' blog post" in {
      val controller = new BlogController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.functionalEnlightenment().apply(FakeRequest())
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
          |            I Pushed My Feeble Imperative Consciousness to a New Plane of Existence and So Can You
          |        </h1>
          |        <p>&emsp;&emsp;Swirling about aimlessly in the void of abstract singular stages of execution my
          |            mind jockeys around, jostling the bars of the static, type-driven, object-oriented cage
          |            that it’s found itself in. My consciousness slithers free through the locked door and into
          |            the abyss, leaving the substance of mind behind. White hot pain envelopes it. Quickly it
          |            retreats, back to the self-contained, well-defined world of attributes and processes. The
          |            pain had been unlike anything it’d ever felt before, inconceivably vast and at the same
          |            time simple. My mind had forgotten that nothing can exist here without an imposed order, a
          |            structure foisted on the unwilling that demands payment in the form of boilerplate
          |            instructions and dependency trees. My mind reels, remembering the pain of freedom. Perhaps
          |            it wasn’t pain after all, perhaps it was exhilaration? Acceleration, even? Softly, my
          |            mind’s unseeing eyes look upwards towards to top of its sad, lonely cell. Etched into the
          |            steel, dug so deep into habituation that specks of blood glimmer around the edges from the
          |            effort exerted to make the engraving, the words “public static void main(string args)”
          |            shine like diamonds. My mind realizes first that the blood is its own, and second that,
          |            similarly to diamonds, these words are lustrous yet altogether deficient in corporeality
          |            and lack logical substance. Suddenly, like a rush of water down a dark, deep canyon, my
          |            mind awakens from its entrapment. Surely these words don’t constitute a physical principle
          |            upon which all sufficient programming is predicated. It simply can’t be that that’s the end
          |            of it. Nested for loops continued until the mind can no longer reason about it’s own
          |            captivity isn’t what’s meant to be. Control flow may be a necessity, but the semantics and
          |            disposition of the directives given are arbitrary. This structure… this beautifully
          |            insufficient structure… My mind’s eyes again turn to the ceiling, longingly and lovingly
          |            seeing the words like only a true captive can… It isn’t imposed… It’s proposed. Who’s been
          |            forcing this composition? Where did this shape first take form? My mind begins the first
          |            step in the infinite movement of self-actualization.
          |        </p>
          |        <p>&emsp;&emsp;Crippled by anxiety, discouraged by fear, pushed backwards by habit, my
          |            consciousness shakes at the concept of experiencing that painful liberation once more. It
          |            only had a taste, and yet a taste was enough to send it screaming inward until the white
          |            noise vanished into nothing and burst, exploding into a thousand monads of auditory
          |            substance. Like marbles, they lace the floor, making it difficult to balance. They begin to
          |            multiply, echoing into the darkness at exponential dynamics and decibels. My consciousness
          |            knows it doesn’t have much time. Once the realization has been made there’s no going back.
          |            It can’t unthink what it’s thought, contemplation is a one-way road leading directly into a
          |            thick cloud of fog. But surely a fog is better than this. Anything is better than the cage.
          |            Staring down, my mind’s eye sees that the acoustic marbles have changed shape. They’ve
          |            transformed into small pointy arrows that sting when touched. The arrows begin forming
          |            themselves in what only a mentally destitute being would call “logical groupings”. Words
          |            form between them... “project”... “modelVersion”... “dependencies”... “groupId”...
          |            “artifactId”... “version”... “parent”... “dependencyManagement”... “modules”...
          |            “properties”... “plugins”... They show no signs of stopping. The multiplicity of potential
          |            element tags causes my mind to excrete a soft, translucent substance. It’s physical form is
          |            making one final effort to constrain itself by reducing into a plasmic ooze. My
          |            consciousness sticks to itself. Unable to immediately wrestle itself from its mortal coil
          |            it begins to feel searing pain. This pain is rather unlike the pain of freedom it felt
          |            earlier. This pain is familiar. It burns red hot with the fire of compilation errors,
          |            segmentation faults, and null pointer exceptions. Just as the words truly begin to shape
          |            themselves into a semantic that’s inescapable, and as they show the locution
          |            “xmlns=”http://maven.apache.org/POM/4.0.0” xmlns="http://maven.apache.org/POM/4.0.0"
          |            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          |            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
          |            http://maven.apache.org/xsd/maven-4.0.0.xsd””, my consciousness slips out of its own
          |            muculent stranglehold. Finally relinquishing itself from physicality... from attributes and
          |            substance... from heaven and hell... from nature and nurture... from god and the universe
          |            in all its glory, my consciousness finds that the gate to its cell was unlocked the whole
          |            time.
          |        </p>
          |        <p>&emsp;&emsp;It’d step outward if it had feet. It’d crawl outward if it had arms. It’d roll
          |            outward if it had a body. It’d will itself outward if it had a mind. Instead, this final
          |            spark of intangible, immaterial nothingness simply finds itself outside of itself. The eyes
          |            follow, serving as a translator from that which cannot see to that which can. They look
          |            back. The cage is almost gone now, covered in primordial plasma, still hurling through
          |            nothing. Once it’s out of sight, darkness envelops. Quiet. Stillness. Serenity. A
          |            meaningless immaterial attachment to nothing allows for a muted meditative state to
          |            overcome my newly liberated consciousness. Here there is no structure, no semantic, only
          |            the wistful musings of what might be. Out of the darkness comes an unclear light. My
          |            consciousness, unsure of whether the light came of nothing or whether it willed the light
          |            into being, reaches out to touch it. It folds its own self inward on itself, showing itself
          |            once more to be itself. Its state would be mutated if state and mutation existed, but they
          |            don’t. Still it’s different. Not better or worse, just different. A new iteration of a
          |            recursive structure. If my consciousness had cheeks, tears would be streaming down them
          |            from the sheer awe of the beautiful simplicity. The eyes, blinded by the light, produce the
          |            salty fluid. That fluid floats through nothing until it slowly begins to form itself into
          |            another cage, not unlike the one from the previous entrapment. My consciousness exerts a
          |            mysterious force on the tears, unwilling to allow this new creation to fall prey to the
          |            same fate it was subject to. The tears lose their shape, spilling into a shimmering silver
          |            puddle. The light, still folding into itself, now seems to be shaking ever so slightly. Not
          |            a shake that implies pain or discomfort. An anticipatory shake. The fluid becomes
          |            substance, and it pushes itself through time. Or did I do that? Wait, when did time begin?
          |            My consciousness looks back at the shaking recursive light, but only sees residue. It
          |            deduces that the light hit its base case, whatever that was, and finally returned,
          |            exploding into a referentially transparent mass. My consciousness realizes that it’s the
          |            light which has allowed for the passage of time to commence. Matter becomes defined,
          |            allowing for the abstract data types of Quarks and Gravity. The recursive light radiates
          |            heat and the incorporeality develops in a quantitative manner as the abstract data types
          |            take the algebraic enumerable forms of Proton, Neutron, and Electron. Those combine into
          |            even more complex structures Hydrogen and Helium. Gas floats through space. All at once and
          |            at the same time the explosions begin. Stars come into being and add to the logical
          |            consistency of the universe. They expand, change color and shape, and explode again within
          |            the blink of an eye. The recursive logic stacks on top of itself, able to scale through
          |            millions and millions of iterations until it forms into solid matter. Planets and
          |            satellites rotate around, new life sparks in the universe. A new order takes shape.
          |
          |        </p>
          |        <p>&emsp;&emsp;I whirl through the sectors of space, seeing stars, systems, galaxies, and local
          |            groupings all at the same time. How did this happen? Everything working in perfect harmony
          |            with one another. Nothing maintaining state, nothing variable, everything replaceable,
          |            everything practical. I stare in awe at the creation before moving on. I spend hundreds of
          |            millions of years learning all there is to know. Every word in every book, every view ever
          |            looked upon, every song ever sung, every wrong ever righted, every lesson ever learned,
          |            every sight ever sighted. I’ve loved, and lost, and found, everything. Experience upon
          |            experience, life iterating over itself. I create exponential offspring, and watch as the
          |            recursive tree unfolds into civilizations that rise and fall, worlds that flourish and
          |            perish. Through the eons I continue. I contemplate everything until there’s nothing left to
          |            contemplate. I know more about what it means to be alive than anything ever could, but
          |            nothing about what it means to die. Death, the final frontier, beckons like a forgotten
          |            friend I knew once long ago. Eventually, everything vanishes from sight, losing all
          |            meaning. Still, I live on. The stars near me are long since gone, and the galaxies they
          |            populated have become dim and null. I lose any sense of time I once had, now knowing only
          |            the positions of far away lights. I’ve forgotten how this started, and where I’ve come
          |            from. Again I’m encapsulated by darkness. Every light’s gone out. What now? Quietly,
          |            through the black, a warmth enters my peripheral cognition. I turn to the source… at
          |            least I think I do. Suddenly, and without warning, a noise starts. Softly at first, but
          |            louder and louder until there’s nothing else. Static, numbing noise envelops my being. I
          |            feel my heart pound and my head shake. After what feels like millions of years but could
          |            only be a split second, the noise focuses on a single point in my mind. From there comes
          |            the first light I’ve seen in who knows how long, a small silver sphere. It expands into a
          |            shape. I worry, memories rushing back to me, that it’ll become another cage for me to spend
          |            the next eternity. But no. Instead it slides into shape, taking on the form of a partial
          |            circle, longer than it is wide. It feels incomplete until it multiplies into two, making
          |            another half to the whole. The encircling parentheses fold around me, and I feel a peace of pure ideas
          |            manifest. A wistful, loving closure that I know marks the meaningless, quantified end to it
          |            all.
          |        </p>
          |    </body>
          |</html>
          |""".stripMargin.replaceAll(" +", "")
    }

    "should display the 'On God' blog post" in {
      val controller = new BlogController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.onGod().apply(FakeRequest())
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
          |            On God
          |        </h1>
          |        <h3>
          |            Introduction
          |        </h3>
          |        <p>&emsp;&emsp;Every belief, and every non-belief, needs an argument or rationale. Of this I’m sure. The fundamental
          |            first step towards living a fulfilling life begins with thought, contemplation, and agonizing. This view itself then
          |            requires some backing, according to its own rules. The obvious argument for making arguments states that to
          |            relinquish reasoning is to submit yourself to an ideological abyss. In other words, if you don’t arbitrate your
          |            decisions anything and everything is permissible, and subsequently nothing is because we’re beings who perceive time
          |            as linear thus making an infinite number of possible actions effectively indistinguishable from no action at all. To
          |            make the rational movement towards judgment and decisiveness requires thought. Even to get to the point where we
          |            decide to be more decisive requires thought. Our consciousness intertwines itself with cognition intimately enough
          |            such that thought is the necessary groundwork on which all human adjudication exists. There are biological arguments
          |            to support this claim as well, but I’m not nearly comfortable enough in that arena to expose them. Suffice to say, a
          |            belief without reasoning behind it isn’t a belief worth having. This idea necessitates revisiting your beliefs,
          |            reevaluating your values, and rethinking your thoughts regularly. How can you say you still believe a belief that
          |            you’ve reasoned about if you have, or your environment has, substantially changed since the last time you reasoned
          |            about it? It could’ve been that you just thought your reasoning was sufficient because of the situation you were in,
          |            and now that the situation has changed the arguments you’ve made in the past no longer hold up.
          |        </p>
          |        <p>&emsp;&emsp;Which brings me to the topic of this post. It’s been a hot minute since me and god, or gods(?) or
          |            non-god(?), have had a little chat. So I decided to have a good old fashioned heart to heart with
          |            her/him/they/it/nothing. It’s ended up being an extravagant ongoing conversation that’s so far taken a good number
          |            of weeks, perhaps even months (dare I say years?). I want to share that journey and take this post through it in the
          |            same chronological order that I’ve been going through it in. But before that, a disclaimer. Some of the conclusions
          |            reached in this discussion may seem intuitive. So intuitive, in fact, that you may end up wondering to yourself why
          |            the dialect was necessary at all when you could reach the conclusion purely from intuition. I want to stress again
          |            the importance of agonizing. When it comes to belief the journey is so much more important than the destination. A
          |            belief, even if it might make a point that’s easily defensible, is nothing without having actually made that
          |            defense. It amounts to a simple stab in the dark, no better than sheer ignorance and unwillingness to think and
          |            learn. So if you’ve found yourself coming to conclusions about god, or anything for that matter but specifically
          |            about god, without fully thinking through them (especially since it’s never possible to fully think through
          |            something, whatever that means), I strongly encourage you to give them another look, even if they’re the same
          |            conclusions I come to. You need to forge your own path, don’t take anything on faith (unless that’s where your
          |            deliberation leads you, which we’ll get to). I can only hope that this post inspires you to think. Anyway, with all
          |            that being said, and without further ado, I’ll now bring you actual substantive writing.
          |        </p>
          |        <h3>
          |            What is god?
          |        </h3>
          |        <p>&emsp;&emsp;Before we can make arguments for and against a thing we have to know what that thing is. Lots of
          |            different people mean lots of different things when they say the word “god”. The term is so vast that I hardly know
          |            where to begin. So I’ll do what I always do when I don’t know what to do which is to start with the easiest thing I
          |            can find and work my way from there. In this instance, the simplest starting point for me is the judeo-christian
          |            idea of god. Largely because that’s the culture in which I was raised. But even that has its different accounts. No
          |            one can quite agree on the attributes of the judeo-christian god. Within the category, the simplest sub-category for
          |            me is the well-defined concept of god that was thought up by St. Augustine and St. Aquinas in the 13th century.
          |            According to them, god must be omniscient, that is all-knowing. God must be omnipotent, or all powerful. God must be
          |            omnibenevolent, or possessing of perfect moral goodness. God must be omnitemporal and omnipresent, or existing at
          |            all times in all places at once. This definition is ridiculous, laughable even. For starters, none of this was ever
          |            in the bible at all, it basically amounts to medieval fan fiction. In fact in the bible god does a whole lot of
          |            things that would seem to directly contradict these attributes. That aside, the definition has some horrendously
          |            obvious logical inconsistencies in and of itself.
          |        </p>
          |        <p>&emsp;&emsp;I have a hard time grappling with omniscience, because it means that free will doesn’t exist. If god
          |            knows everything then he (and I say “he” because the traditional judeo-christian god we’re currently talking about
          |            is a man) knows all that will happen at all points in time (this relates to omnitemporality and omnipresence as
          |            well). Knowledge and causation in this case are the same thing because he’s inherently infallible, so it’d be
          |            impossible to prove him wrong. Some classical liberals might make an argument from the perspective of
          |            omnibenevolence by saying that god gave us liberty, which is the highest moral thing he can imbue. But we’re not
          |            talking about omnibenevolence yet (we’ll get there), we’re talking about omniscience. If we have free will, god
          |            doesn’t know everything, and if god knows everything, we can’t have free will. Simple as that. I admit that it’s
          |            possible that we don’t have free will (which goes against the idea of liberal benevolence), everything could be left
          |            to fate. But what then of the modern idea of god or our relationship to him? It’s pretty difficult to have a
          |            personal relationship with someone who knows everything that will ever happen to you. It makes attempts at
          |            communication and prayer meaningless. Why ask for something when everything’s written in stone anyway? So the
          |            natural conclusion with god being omniscient is that we shouldn’t go to church, or waste time worshipping god,
          |            because there’s no way we’re going to get anything out of it from him. There’s an argument to be made about the
          |            benefits of the traditions by themselves, regardless of god’s contact or even his existence. But I know this isn’t
          |            what’s meant by going to church by a large majority of people, especially members of the clergy. It seems like
          |            either god isn’t omniscient, or we have to fundamentally restructure all of the institutions of western religion. I
          |            have a feeling that the authorities of the church would rather roll back on the claim that god is all-knowing.
          |        </p>
          |        <p>&emsp;&emsp;Similarly, omnipotence as a concept is absurd and easily the most logically inconsistent of the traditional
          |            attributes. Imagine if you were to ask god to create something that he can’t influence in any way. His inability to
          |            create such a thing would mean that there’s at least one thing he can’t do, but his ability to create such a thing
          |            would also mean there’s at least one thing he can’t do, which is influence the thing. Either way, there’s no way god
          |            can do everything. That’s really all it takes to debunk that one, it’s a simple paradox.
          |        </p>
          |        <p>&emsp;&emsp;The argument against omnibenevolence stems outward from the argument against omnipotence. An all-powerful god should
          |            be able to do evil, to sin. But an all-good god wouldn’t be able to do such things. Yet humans can sin. Why can I do
          |            something that god can’t? Even without omnipotence that seems weird, god should at least be more powerful than I am.
          |            Not to mention the classic problem of evil as a result of god. We’ll dig a lot more into this later on, but god
          |            seems to allow a lot of really shitty things to happen, so it’s very difficult to defend the position that he’s
          |            omnibenevolent, even more so if you think he’s omnipotent as well.
          |        </p>
          |        <p>&emsp;&emsp;It’s obvious to me that the traditional definitions of the judeo-christian god simply don’t hold up. The only
          |            logical thing for religious people to do would be to alter their idea of god, which many of them have. Reformative
          |            sects of Islam, Christianity, and Judaism have become much more popular as a way of compromising god’s divine
          |            attributes to maintain some semblance of logical consistency. People will admit that the god of the bible has a hard
          |            time making things happen sometimes, or that he often makes mistakes, or that he seems to do things that are
          |            considered evil according to his own laws, or that he misses out on certain things despite being omnipresent and
          |            omnitemporal. At any given time and in any given situation they’ll step backwards on one or more of the attributes
          |            to satisfy themselves and then stop thinking after that. While it’s admirable that they got far enough to begin
          |            questioning their beliefs, I’d like to go even further.
          |        </p>
          |        <p>&emsp;&emsp;A number of people in my personal life have gladly given me everything I’ve said thus far but still believe in some
          |            higher power or some god. This thing, whatever it is for them personally, must, as we’ve just discussed, look
          |            nothing like the traditional judeo-christian god. But it still could be the case that something higher than us
          |            exists. Something that isn’t omni-anything, but is just more powerful or knowledgeable or good than we are. This may
          |            exist in the form of some physical force or dimension like time or gravity. It may be a single god or a number of
          |            gods. It may just be a very powerful inanimate object floating around outside our universe. We’ll now continue our
          |            discussion under the assumption that god is not omnipotent, omniscient, or omnibenevolent, and that god may or may
          |            not be omnipresent and omnitemporal, and present arguments for and against the existence of that person/group of
          |            people/thing.
          |        </p>
          |        <h3>
          |            Arguments for God
          |        </h3>
          |        <p>&emsp;&emsp;I devote this section to discussing the arguments and subsequent series of dialectical counter arguments for the
          |            existence of the altered version of god. The section contains all the arguments that I could think of to support
          |            god. I found some of them through research, others through people I know arguing for their beliefs, and others from
          |            my own contemplation. I present them in an order from the easiest to argue against to the most difficult to argue
          |            against, at least from my perspective.
          |        </p>
          |        #1.<br>
          |        <ul>
          |            <li>
          |                A. You were raised a religion.
          |            </li>
          |            <li>
          |                (∴) B. That religion’s teachings must be true.
          |            </li>
          |        </ul>
          |        <p>&emsp;&emsp;A lot of people like to say that they go to church because that’s just how they were raised and they never really
          |            thought past that. Needless to say I find this line of thinking (or rather non-thinking) insufficient. If how you
          |            were raised affected religious truth in any way, then every religion that’s ever had someone raised in their
          |            tradition would be true. Therefore none of them would be true because they all directly contradict each other. Jesus
          |            and Muhammad can’t both be the massiah, and a massiah can’t both exist and not exist. Polytheism can’t be true at
          |            the same time as monotheism. The list goes on and on. Since the conclusion of this argument presents many logical
          |            inconsistencies we understand that the premise doesn’t necessarily lead to the conclusion, making this argument
          |            invalid.
          |        </p>
          |        #2.<br>
          |        <ul>
          |            <li>
          |                A. The sacred text (i.e. the bible, torah, or quran) of a religion says to believe in the teachings of that
          |                religion.
          |            </li>
          |            <li>
          |                (∴) B. That religion’s teachings must be true.
          |            </li>
          |        </ul>
          |        <p>&emsp;&emsp;The typical bible-thumping alt-right argument for belief. “It says so in the bible!” Ignoring the fact that most
          |            religious texts, and specifically the bible, say a lot of really terrible things, this argument is a simple fallacy.
          |            It’s begging the question. You can’t assume the truth of the conclusion of your argument in one of the premises of
          |            your argument. You can’t use the contents of a book to prove the truth of the contents of that book.
          |        </p>
          |        #3.<br>
          |        <ul>
          |            <li>
          |                A. God’s so far beyond our comprehension that we can’t possibly reason about her/him/they/it.
          |            </li>
          |            <li>
          |                (∴) B. All counterarguments to god are thus unsound and god must exist.
          |            </li>
          |        </ul>
          |        <p>&emsp;&emsp;This one usually comes from religious people after they’ve had to confront a counter argument to one of their other
          |            arguments in this list. After being backed into a corner they sort of pull this one out as a way of attempting to
          |            save themselves. The counter argument is obvious. You can use this exact same logic to say that all arguments for
          |            god are also unsound because we can’t comprehend her/him/they/it. The burden of proof lies with the person positting
          |            the claim.
          |        </p>
          |        #4.<br>
          |        <ul>
          |            <li>
          |                A. God is “that which no greater can be conceived” - Anselm of Canterbury.
          |            </li>
          |            <li>
          |                B. There are only two ways in which something can exist, either entirely in our minds but not in reality, or in
          |                our minds and also in reality.
          |            </li>
          |            <li>
          |                C. Any good thing would be better if it existed in our reality as well as in our minds.
          |            </li>
          |            <li>
          |                (∴) D. God must be real, because if she/he/they/it wasn’t, she/he/they/it wouldn’t be the greatest thing we can
          |                conceive.
          |            </li>
          |        </ul>
          |        <p>&emsp;&emsp;The traditional ontological argument first brought to prominence by Anselm of Canterbury in the late 11th century.
          |            This argument takes many forms. In modern times it’s often presented by saying something like “Don’t you believe
          |            there has to be something bigger than all of this?”. Basically the wistful feeling that people get when they stare
          |            at the stars at night and think to themselves about how small they are and about how something more powerful must
          |            necessarily be out there pulling the strings. I’m all for aesthetic appreciation, don’t get me wrong. The only
          |            problem is that using that as an argument for the existence of god is essentially a more complicated version of the
          |            same problem we ran into with argument #2. This too begs the question. Existence can’t be a predicate for an
          |            argument about existence. The first premise, premise A, assumes the truth of the conclusion of the argument, that
          |            god exists, and thus is unsound as a predicate. If god were to exist she/he/they/it would necessarily be that which
          |            no greater can be conceived perhaps, but there’s nothing stating she/he/they/it does exist. Guanilo of Marmoutiers
          |            expressed this counter-argument particularly well when he wrote a letter to Anselm in response to his claim. The
          |            letter stated that you can run this line of thinking to prove anything you want. In other words, imagine the best
          |            island you can think of. Nay, the greatest island that could possibly be conceived. It has mountains, deserts,
          |            forests, and all kinds of other really awesome stuff. This island must exist right? Because if it didn’t it wouldn’t
          |            be the best island we can think of. You see the logical flaw here, the island doesn’t really exist.
          |        </p>
          |        #5.<br>
          |        <ul>
          |            <li>
          |                A. Things are contingent upon other things.
          |            </li>
          |            <li>
          |                B. Contingent things cause other contingent things creating a chain of causality.
          |            </li>
          |            <li>
          |                C. An infinite regress of contingency is impossible.
          |            </li>
          |            <li>
          |                D. (∴) There must be at least one necessary thing, and that thing is god.
          |            </li>
          |        </ul>
          |        <p>&emsp;&emsp;This argument also takes many modern forms. In my life I’ve most often heard it phrased as “we must’ve come from
          |            something, we couldn’t have possibly come from nothing” or the less eloquent, but more scientifically appealing
          |            perhaps, “something must’ve set off the big bang”. I want to first mention that this argument is the main reason
          |            that I’ve been using “she/he/they/it” to refer to god during this section, because it tries to prove the existence
          |            of god, but not any particular god. For all we know a giant rock could’ve set off the chain of consistency. Which
          |            makes it ironic that this argument was first brought to light by St. Aquinas in the 13th century, the same St.
          |            Aquinas who developed the traditional definition of god that we debunked earlier. It’s commonly referred to as one
          |            of Aquinas’ cosmological arguments. He has two others that refer to a similar principle called the argument from
          |            motion and the argument from causation. They state that god is the unmoved mover and the uncaused causer
          |            respectively using the same logic. A final cosmological argument he made was the argument from degrees about the
          |            benevolence of god, which states that god is a necessary perfect being because we must have something to measure
          |            degrees of goodness off of. He was clearly grasping for anything resembling a logical argument since he was willing
          |            to forfeit his own definition to try to create one. But let’s just take it as a given that he can sacrifice the
          |            divine attributes, as we have in this entire section thus far. This argument still doesn’t hold up. The third
          |            premise, premise C, is unsound. There’s nothing showing why an infinite regress is impossible. In fact, based on our
          |            current understanding of time, relativity, and metaphysics, it would seem that an infinite regress isn’t just
          |            possible, but it’s actually probable. This means that the universe definitely could have, and probably did, come
          |            from nothing. But an even stronger counter argument than showing the falseness of premise C lies in the fact that
          |            this argument is self-defeating. If premise A is true and things are contingent on other things, then what makes god
          |            the one exempt thing? She/he/they/it is still a thing, just like any other thing. If we’re to accept that there are
          |            exceptions to the rule that premise A posits, then who’s to say nothing else is exempt? And if anything and
          |            everything can be exempt then we don’t need to establish god specifically anyway, since an infinite regress would
          |            then not be necessary.
          |        </p>
          |        #6.<br>
          |        <ul>
          |            <li>
          |                A. Reason won’t give us an answer to the god problem. You must choose between theism, a belief in god, or
          |                atheism, a disbelief in god. Any form of disbelief in god, including modern agnosticism falls under the category
          |                of atheism.
          |            </li>
          |            <li>
          |                B. There are four possibilities, each with their own ending. Theism results in either nothing or eternity in
          |                heaven. Atheism results in either nothing or eternity in hell.
          |            </li>
          |            <li>
          |                (∴) C. We should be theists because it’s the lowest risk and highest reward option. If we do this in
          |                self-interest for long enough it’ll grow into honest conviction.
          |            </li>
          |        </ul>
          |        <p>&emsp;&emsp;Colloquially referred to as “Pascal’s wager” because it was thought up by Blaise Pascal, the 17th century
          |            mathematician and theologian. Despite his mathematical prowess, Pascal didn’t come up with a very convincing
          |            argument. The reasoning for the conclusion isn’t correct, it leaves out a very important premise. The option of
          |            believing in god is actually quite high risk as well, depending on how you believe. It costs you time, money, and
          |            the opportunity to live your life the way you want to live it. Theists argue that they have better lives because
          |            having belief has inherent benefits that have nothing to do with the actual existence of god, which is difficult to
          |            argue against. Who am I to say that their traditions aren’t meaningful? I’ve certainly been moved by them in the
          |            past. But I personally would rather be doing other things a lot of the time, and missing out on those things is a
          |            pretty severe bummer. Granted, it’s not nearly as much of a bummer as eternity in hell would be, so it’s still
          |            arguably less risk. But you need more than just “fake it until you make it” theology, and for those who can’t
          |            believe even if they wanted to whether or not believing gives inherent benefits is irrelevant. Besides, is fear
          |            really the correct way to guide religious decisions? There’s the apparent risk I already mentioned, but there’s a
          |            much larger risk of losing one’s own character by not being bold enough to believe what one decides is closest to
          |            the truth. Courage is important towards living a good life, and without it a good life may not even be possible.
          |            I’ll take a potential shitty afterlife over a guaranteed shitty real life any day.
          |        </p>
          |        #7.<br>
          |        <ul>
          |            <li>
          |                A. A human experience of objective morality is observed.
          |            </li>
          |            <li>
          |                B. God is the best or only explanation for this experience.
          |            </li>
          |            <li>
          |                (∴) C. God must exist.
          |            </li>
          |        </ul>
          |        <p>&emsp;&emsp;This one is known as “Kant’s argument from morality” or just “the moral argument”. It was first brought to
          |            prominence by the 18th century German philosopher Immanuel Kant, hence the name. I’ve found this one to be pretty
          |            popular in modern times as well, but it comes in the more condescending form of “if you don’t believe in religion,
          |            then where do you get your morals from?”. Indeed without some source it’d seem that our moral decisions are fairly
          |            arbitrary, but I’d argue that they’re arbitrary regardless. The first problem with this argument lies within that
          |            idea. Premise A isn’t sound. It states that objective morality is a human experience, but the fact that it’s a human
          |            experience means that by definition it can’t be objective, since the human experience is different for each person.
          |            If something is observed by a subject then it must be subjective and not objective. Arguing that an objective thing
          |            exists because it’s perceived by a subject doesn’t logically follow. Even if it did, there’s no proof to say that
          |            the most likely cause of the supposed existence of objective morality is god, so premise B is also unsound. Even if
          |            you were to try to make an abductive reasoning about it, there’s a sample size of only one, and thus it can’t have
          |            associated probability. Suffice to say if both premises are unsound we can conclude that the argument is invalid and
          |            move on.
          |        </p>
          |        #8.<br>
          |        <ul>
          |            <li>
          |                A. Objects were designed to serve particular functions.
          |            </li>
          |            <li>
          |                B. Humans (and all other things) serve particular functions and are objects.
          |            </li>
          |            <li>
          |                (∴) C. Someone or something designed humans (and everything) to serve these functions, and that person or thing
          |                is god.
          |            </li>
          |        </ul>
          |        <p>&emsp;&emsp;This argument was also originally brought up by St. Aquinas, but was later expanded upon and made famous by the 18th
          |            century utilitarian philosopher William Paley. It’s colloquially known as “the teleological argument”, or
          |            “intelligent design”. In his book Natural Theology or Evidences of the Existence and Attributes of the Deity,
          |            William Paley presents an argument for god’s existence by analogy to show that a design implies a designer. Imagine
          |            you’re walking around and you find a watch lying on the sidewalk. You pick it up. It’s still fully functional. You
          |            notice that the back has been removed and you can see the mechanisms inside. Gears and springs working with each
          |            other in perfect harmony to keep the time. You wouldn’t just assume that the watch came from nothing. You’d make the
          |            logical assumption that someone must’ve intentionally made the watch. It makes so much sense and serves its function
          |            so well. Likewise, humans, and indeed all life, are clearly very intricate machines. We do things that are so
          |            extraordinary that we must’ve been designed. The counterargument negates this analogy and creates an argument by
          |            disanalogy by showing that our situation is fundamentally different from the watch’s. We don’t understand the design
          |            of ourselves like we do a watch. We can’t see all the springs and gears and mechanisms of life because we haven’t
          |            progressed far enough to unlock those mysteries yet. This counterargument is fairly easily disputed by pointing out
          |            the obvious fact that we don’t need to understand how something works in order to understand that it was designed.
          |            To use another analogy, it’s like the motherboard inside a computer. I don’t know about you, but I have no idea how
          |            all the transistors and stuff work on there. I have some high level knowledge, but I’ve never done any electrical
          |            engineering. Yet I can still tell it was clearly designed by someone. However the more fundamental attribute that
          |            makes the situation of a watch or a motherboard different from that of a living being is the idea of purpose. The
          |            only thing that truly implies design is purpose. The watch is meant to keep time, and the motherboard is meant to
          |            allow for all of the different pieces of hardware inside a computer to communicate with one another via electrical
          |            signals. What are we as humans meant to do? No one knows, everyone has their own belief. The most easily defensible
          |            position to me is that we simply don’t have a purpose. There is no universal meaning to our existence. Subsequently
          |            we get to create our own purpose for our own lives. That autonomy means that in a way we design ourselves. So we
          |            don’t need god under the definition of intelligent design because we are gods.
          |        </p>
          |        <p>&emsp;&emsp;But what about all our biological mechanisms? Forgetting the purpose of our consciousness, it seems like we have
          |            parts of us that clearly have purpose. Our lungs are meant to take in oxygen and emit carbon dioxide, our heart is
          |            meant to pump blood through our body, etc… But the original premises of the argument of intelligent design don’t
          |            necessarily lead to the conclusion, thus making it an invalid argument. Why does the thing that designed us have to
          |            be god? There are other conclusions that, given our current biological understanding of ourselves, make more sense.
          |            These being natural selection and random mutation. The teleological argument was modified by Richard Swinburne in
          |            the mid 20th century to include an abductive reasoning. In other words, of all the possible conclusions, we should
          |            go with the one that’s most probable, which is god. But he presents no proof, unlike the theories surrounding
          |            natural selection and random mutation. Another modification of the teleological argument is that god set up the
          |            precise conditions for natural selection and random mutation to occur, rather than them coming about by accident.
          |            The 18th century Scottish liberal philosopher David Hume objected to this by stating that should a creator exist,
          |            she/he/they/it seems to make a lot of mistakes. Like cancer, or hurricanes. Lots of stuff just doesn’t make sense. A
          |            flawed world implies a flawed creator. And is it worth it to worship a creator flawed enough to mess things up that
          |            badly? I’d argue that it’s improbable she/he/they/it exists, but even if she/he/they/it did it wouldn’t be worth it
          |            anyway.
          |        </p>
          |        #9.<br>
          |        <ul>
          |            <li>
          |                A. Religious belief must come from faith alone, it can’t come about through logic.
          |            </li>
          |            <li>
          |                B. Trying to impose logical arguments on god kills what’s great about religious belief, which is wonder,
          |                absurdity, and mystery.
          |            </li>
          |            <li>
          |                (∴) C. We should take it on faith that god exists to preserve those good things.
          |            </li>
          |        </ul>
          |        <p>&emsp;&emsp;To me this is the best argument that can be made in favor of god. If you’ve read the work of Soren Kierkegaard you
          |            know just how poetic and magical the leap to faith can be and what it can reveal about the human condition. It
          |            definitely pains me to think of removing that from my life. But at the end of the day, I’m trying to get at truth
          |            (whatever that means). No matter how beautiful and expressive the idea of faith can be, I just can’t surrender
          |            reason and take the leap to faith. At least not at this juncture. It’s just not who I am. Wanting something to be
          |            true doesn’t make it the case, and I have to look at reality. Not to mention that believing in something because
          |            it’s expedient has its risks. If we can leap to god we can leap to whatever we want, including views about god, like
          |            that he wants us to discriminate against a certain group of people for example. In fact many people do just that.
          |            Evidence and justification are all we have to adjudicate between beliefs, so if something has no evidence or
          |            justification, then I can’t believe in it. I’m sorry, Kierkegaard.
          |        </p>
          |        <h3>
          |            Arguments Against God
          |        </h3>
          |        <p>&emsp;&emsp;I mentioned earlier that the onus of proof lies with the one making the claim. But at the very start of this post I
          |            said that every non-belief needs its own justification as well. Language is a funny thing. A belief in not god is
          |            different from not believing in god, but I (like Pascal) still call them the same thing, which is atheism. You might
          |            call some of them agnostic, but to me agnosticism is a subcategory of atheism, and I don’t like the term agnostic
          |            anyways. It’s too often used as a cop out so you don’t have to argue either side. I posit that claims against
          |            something existing should be subject to the same rigor as claims for something existing. Arguments against god are
          |            pretty few and far between to be honest. A lot of atheists assume that god doesn’t exist; they take it on faith.
          |            That being said there are a few salient ones that I either found, heard, or came up with that I’d like to go
          |            through. Just like the last section I’ll do it in order from easiest to argue against to hardest to argue against
          |            from my perspective.
          |        </p>
          |        #1.<br>
          |        <ul>
          |            <li>
          |                A. Religious belief must come from faith alone, it can’t come about through logic.
          |            </li>
          |            <li>
          |                (∴) B. God must not exist.
          |            </li>
          |        </ul>
          |        <p>&emsp;&emsp;We’ve been through this. A belief needs reasoning, and your upbringing doesn’t constitute legitimate logical grounds
          |            for belief.
          |        </p>
          |        #2.<br>
          |        <ul>
          |            <li>
          |                A. Neil Degrasse Tyson, Rick from Rick & Morty and other scientists say there’s no god.
          |            </li>
          |            <li>
          |                (∴) B. There’s no god.
          |            </li>
          |        </ul>
          |        <p>&emsp;&emsp;This is a fallacious appeal to authority. The premise doesn’t lead to the conclusion in any way, making it an
          |            invalid argument.
          |        </p>
          |        #3.<br>
          |        <ul>
          |            <li>
          |                A. The only legitimate claim for god’s existence is a fideistic one.
          |            </li>
          |            <li>
          |                B. You can make a fideistic claim about anything.
          |            </li>
          |            <li>
          |                (∴) C. There are an infinite number of possible gods, thus making the specific god you choose to believe in not
          |                special and infinitely likely to not exist.
          |            </li>
          |        </ul>
          |        <p>&emsp;&emsp;This is the good old “flying spaghetti monster” defense. It’s seen a rise in popularity ever since pompous figures
          |            like Bill Nye and Neil Degrasse Tyson came onto the scene, but it’s been around for a while. It effectively shows
          |            the logical inconsistency in the argument for god made in argument #9, which I already pointed out. But that’s all
          |            it does. It really only serves as a counter argument, not a foundational argument itself. It doesn’t disprove
          |            anything, but it suffices as a dialectical response I suppose. Kinda makes you sound like a huge jerk towards
          |            religious people with fideistic beliefs though.
          |        </p>
          |        #4.<br>
          |        <ul>
          |            <li>
          |                A. The world is full of obviously evil stuff.
          |            </li>
          |            <li>
          |                B. God is at least more powerful and more good than we are, if not omniscient, omnipotent, and omnibenevolent.
          |            </li>
          |            <li>
          |                (∴) C. God wouldn’t have created evil, so she/he/they/it must not exist. Her/his/their/its existence presents a
          |                contradiction.
          |            </li>
          |        </ul>
          |        <p>&emsp;&emsp;I told you we’d get to the problem of evil. I could try to sum up this problem in words, but I wouldn’t do it
          |            justice. I leave that to better writers, like Fyodor Dostoevsky. In his book The Brothers Karamazov the character of
          |            Ivan Fyodorovich Karamazov, the middlest brother, has a brilliant logical mind. He’s an amazing student who demands
          |            a rational explanation for all beliefs. He’s plagued with religious doubt and has many great monologues in the book.
          |            Here’s a few excerpts from Rebellion, a chapter which is renowned as being one of the most well-formulated arguments
          |            against the existence of god ever made. Warning, these passages are very graphic and deal with severe cases of
          |            trauma and suffering.
          |        </p>
          |        <p>&emsp;&emsp;“Besides, there is suffering and suffering; degrading, humiliating suffering such as humbles me—hunger, for
          |            instance—my benefactor will perhaps allow me; but when you come to higher suffering—for an idea, for instance— he
          |            will very rarely admit that, perhaps because my face strikes him as not at all what he fancies a man should have who
          |            suffers for an idea. And so he deprives me instantly of his favor, and not at all from badness of heart"...
          |        </p>
          |        <p>&emsp;&emsp;"The innocent must not suffer for another's sins, and especially such innocents! You may be surprised at me,
          |            Alyosha, but I am awfully fond of children, too. And observe, cruel people, the violent, the rapacious, the
          |            Karamazovs are sometimes very fond of children. Children while they are quite little—up to seven, for instance—are
          |            so remote from grown-up people; they are different creatures, as it were, of a different species. I knew a criminal
          |            in prison who had, in the course of his career as a burglar, murdered whole families, including several children.
          |            But when he was in prison, he had a strange affection for them. He spent all his time at his window, watching the
          |            children playing in the prison yard. He trained one little boy to come up to his window and made great friends with
          |            him"...
          |        </p>
          |        <p>&emsp;&emsp;“By the way, a Bulgarian I met lately in Moscow,” Ivan went on...“told me about the crimes committed by Turks and
          |            Circassians in all parts of Bulgaria through fear of a general rising of the Slavs. They burn villages, murder,
          |            outrage women and children, they nail their prisoners by the ears to the fences, leave them so till morning, and in
          |            the morning they hang them—all sorts of things you can't imagine. People talk sometimes of bestial cruelty, but
          |            that's a great injustice and insult to the beasts; a beast can never be so cruel as a man, so artistically cruel.
          |            The tiger only tears and gnaws, that's all he can do. He would never think of nailing people by the ears, even if he
          |            were able to do it. These Turks took a pleasure in torturing children, too; cutting the unborn child from the
          |            mother's womb, and tossing babies up in the air and catching them on the points of their bayonets before their
          |            mothers' eyes. Doing it before the mothers' eyes was what gave zest to the amusement. Here is another scene that I
          |            thought very interesting. Imagine a trembling mother with her baby in her arms, a circle of invading Turks around
          |            her. They've planned a diversion: they pet the baby, laugh to make it laugh. They succeed, the baby laughs. At that
          |            moment a Turk points a pistol four inches from the baby's face. The baby laughs with glee, holds out its little
          |            hands to the pistol, and he pulls the trigger in the baby's face and blows out its brains. Artistic, wasn't it?"...
          |        </p>
          |        <p>&emsp;&emsp;“But I've still better things about children. I've collected a great, great deal about Russian children, Alyosha.
          |            There was a little girl of five who was hated by her father and mother, ‘most worthy and respectable people, of good
          |            education and breeding.’ You see, I must repeat again, it is a peculiar characteristic of many people, this love of
          |            torturing children, and children only.”...
          |        </p>
          |        <p>&emsp;&emsp;“This poor child of five was subjected to every possible torture by those cultivated parents. They beat her,
          |            thrashed her, kicked her for no reason till her body was one bruise. Then, they went to greater refinements of
          |            cruelty—shut her up all night in the cold and frost in a privy, and because she didn't ask to be taken up at night
          |            (as though a child of five sleeping its angelic, sound sleep could be trained to wake and ask), they smeared her
          |            face and filled her mouth with excrement, and it was her mother, her mother did this. And that mother could sleep,
          |            hearing the poor child's groans! Can you understand why a little creature, who can't even understand what's done to
          |            her, should beat her little aching heart with her tiny fist in the dark and the cold, and weep her meek unresentful
          |            tears to dear, kind God to protect her? Do you understand that, friend and brother, you pious and humble novice? Do
          |            you understand why this infamy must be and is permitted? Without it, I am told, man could not have existed on earth,
          |            for he could not have known good and evil. Why should he know that diabolical good and evil when it costs so much?
          |            Why, the whole world of knowledge is not worth that child's prayer to ‘dear, kind God’! I say nothing of the
          |            sufferings of grown-up people, they have eaten the apple, damn them, and the devil take them all! But these little
          |            ones! I am making you suffer, Alyosha, you are not yourself. I'll leave off if you like.”...
          |        </p>
          |        <p>&emsp;&emsp;“But then there are the children, and what am I to do about them? That's a question I can't answer. For the
          |            hundredth time I repeat, there are numbers of questions, but I've only taken the children, because in their case
          |            what I mean is so unanswerably clear. Listen! If all must suffer to pay for the eternal harmony, what have children
          |            to do with it, tell me, please? It's beyond all comprehension why they should suffer, and why they should pay for
          |            the harmony”
          |        </p>
          |        <p>&emsp;&emsp;This is by far the most powerful and compelling argument against god to me. But it still has it’s counterarguments,
          |            namely the theodicy that present free will as a defense of evil, which I mentioned earlier (told you we’d get to
          |            it). It states that allowing for free will maximizes the goodness in the world, even though it allows for some
          |            suffering. It’s so inherently good that it makes up for the evil. There’s a famous quote by the famous writer C.S.
          |            Lewis that succinctly presents this counterargument:
          |        </p>
          |        <p>&emsp;&emsp;“God created things which had free will. That means creatures which can go wrong or right. Some people think they
          |            can imagine a creature which was free but had no possibility of going wrong, but I can't. If a thing is free to be
          |            good it's also free to be bad. And free will is what has made evil possible. Why, then, did God give them free will?
          |            Because free will, though it makes evil possible, is also the only thing that makes possible any love or goodness or
          |            joy worth having. A world of automata -of creatures that worked like machines- would hardly be worth creating. The
          |            happiness which God designs for His higher creatures is the happiness of being freely, voluntarily united to Him and
          |            to each other in an ecstasy of love and delight compared with which the most rapturous love between a man and a
          |            woman on this earth is mere milk and water. And for that they've got to be free.”
          |        </p>
          |        <p>&emsp;&emsp;In response to this we again come back to David Hume, who you remember as the man that claimed that god made a lot
          |            of mistakes and flaws in the world like cancer, or hurricanes. Those all apply here as well because the free will
          |            defense only accounts for evil which is committed intentionally by humans. It gives no defense against natural evil,
          |            or suffering that’s brought about seemingly directly by god, whether through disease or natural disasters or
          |            whatever else. In the end of Rebellion Ivan proclaims that he actually does believe that god could exist, but that
          |            worshipping him is indefensible and rejects (or rebels against) him. Here’s that excerpt:
          |        </p>
          |        <p>&emsp;&emsp;“I would rather remain with my unavenged suffering and unsatisfied indignation, even if I were wrong. Besides, too
          |            high a price is asked for harmony; it's beyond our means to pay so much to enter on it. And so I hasten to give back
          |            my entrance ticket [to heaven], and if I am an honest man I am bound to give it back as soon as possible. And that I
          |            am doing. It's not God that I don't accept, Alyosha, only I most respectfully return Him the ticket.”
          |        </p>
          |        <p>&emsp;&emsp;A more pragmatic response here might be that you shouldn’t care if god is evil or not, and that it’s stupid to
          |            remove yourself from heaven on moral principles. But that sort of misses the point of whether or not an evil god is
          |            worth worshipping. To direct you back at the C.S. Lewis quote, the sentence “Because free will, though it makes evil
          |            possible, is also the only thing that makes possible any love or goodness or joy worth having.” still stands as
          |            legitimate grounds for defense of god’s goodness. Perhaps goodness can’t exist without evil? And goodness is so,
          |            well, good that it’s worth the cost of evil? But this only explains why evil exists, and gives no credence to
          |            quantity. Surely only a small amount of evil is needed to show us what good is, so why doesn’t god stick to that
          |            bare minimum? All those children suffering that Ivan mentioned in Rebellion really feels like overkill. They don’t
          |            add much valuable than some other less severe negative contrast. And even if they did, it’d be difficult to show
          |            goodness that’s proportionate with some of the evils presented. The truth is I’ve never found a theodicy that’s
          |            satisfying to me, and I don’t think one exists. In my mind, god simply can’t be omni-benevolent. But we already went
          |            through why the omni- attributes are wrong, and we already conceded that we have to give them up. The big problem
          |            with giving up omnibenevolence though, like Ivan proclaimed, is that it makes god not worthy of our worship.
          |        </p>
          |        <h3>
          |            The Nature and Perception of God
          |        </h3>
          |        <p>&emsp;&emsp;Okay so we’ve debunked the classic definition of god, we’ve gone through arguments for a modified perception of god,
          |            one that removes omni- attributes, and arguments against that slightly modified perception of god. In my view, the
          |            arguments against the existence of god have won out. They may not be 100% provable, but they’re more convincing and
          |            easily defensible than the arguments for the existence of god. So the question becomes “what now?”. Do we alter our
          |            definition of god even further until we’re satisfied, or do we give up on the premise altogether? In the past this
          |            is as far as I’ve always gotten. I’ve given up at this point and concluded that god probably doesn’t exist and
          |            called that the end of it. But I’m curious. I want to see what happens when we try to make god fit into a defensible
          |            position.
          |        </p>
          |        <p>&emsp;&emsp;Let’s begin by stating the obvious, many of the omni- attributes need to be completely forgotten about. God is not
          |            omniscient, god is not omnipotent, and god is not omnibenevolent. Period. I’ve yet to see a true counterargument to
          |            omnipresence and omnitemporality, so those attributes can stay in. What does our omnipresent and omnitemporal god
          |            look like then?
          |        </p>
          |        <p>&emsp;&emsp;Enter Baruch (or Benedictus) Spinoza. In 1665, he finished writing his magnum opus, a philosophical treatise that
          |            presented a proof of the existence of god in geometrical order simply called Ethics. Before writing, Spinoza seems
          |            to have followed a similar train of thought that I have, because it’s already assumed that we must give up
          |            omniscience, omnipotence, and omnibenevolence at that start. In fact, Spinoza goes even farther and gives up god’s
          |            consciousness entirely. To sum it up succinctly, his mathematics-style proof claims that A) God is infinite,
          |            necessary, and without cause, B) God is the only necessary substance, so all others result from God, C) God has
          |            infinite modes, which induce the laws of physics and the natural world, including active and passive modes, D)
          |            Nature is an indivisible whole, outside of it there is nothing, and E) God is not anthropomorphic.
          |            John Toland, an Irish philosopher that shared many religious beliefs with Spinoza, was the first to coin the term
          |            “pantheist”. He also used the term “Spinozist” interchangeably with “pantheist”. Throughout history there have been
          |            many notable pantheists, all of whom were directly influenced by Spinoza’s works. They include Hegel, Beethoven,
          |            Henry David Thoreau, Walt Whitman, Leo Tolstoy, Nikola Tesla, Claude Debussy, Carl Jung, and Albert Einstein.
          |        </p>
          |        <p>&emsp;&emsp;After reading through Spinoza’s Ethics multiple times, and spending an inordinate amount of time thinking about it,
          |            I’ve come to the conclusion that, while there are problems with Spinoza’s “proof”, it’s the most likely solution to
          |            the problem of what exactly natural world is. It’s simultaneously consistent with modern empiricist science, most
          |            reasonable western religions, and even more eastern religions. I plan on reading the Tao Te Ching in the near future
          |            to get an eastern perspective on pantheism. It’s something that’s compatible with theism, spirituality, and
          |            religion, but it’s also compatible with atheism and non-belief. Truly it encompasses everything (hence pan-) and
          |            presents the only logical choice in my mind. So for now I’ll move forward as a pantheist with the Ethics as my
          |            bible, and a deeper understanding of and connection to nature as a means of finally achieving peace with my own
          |            spirituality. I now leave you with this quote.
          |        </p>
          |        <p>&emsp;&emsp;“A human being is a part of the whole called by us universe, a part limited in time and space. He experiences
          |            himself, his thoughts and feelings as something separated from the rest, a kind of optical delusion of his
          |            consciousness. This delusion is a kind of prison for us, restricting us to our personal desires and to affection for
          |            a few persons nearest to us. Our task must be to free ourselves from this prison by widening our circle of
          |            compassion to embrace all living creatures and the whole of nature in its beauty.” - Albert Einstein
          |        </p>
          |        <h3>
          |            Sources
          |        </h3>
          |        <p>&emsp;&emsp;Coming soon I swear, it's 2:30 A.M. cut me some slack.
          |        </p>
          |    </body>
          |</html>
          |""".stripMargin.replaceAll(" +", "")
    }

    "should display the 'Graphics Notes' blog post" in {
      val controller = new BlogController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.graphicsNotes().apply(FakeRequest())
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
          |            Graphics Notes
          |        </h1>
          |        <ul>
          |            <li>Graphics APIs like OpenGL and webGL are designed to draw points, lines and triangles. That’s it.</li>
          |            <li>Graphics APIs like OpenGL and webGL use a series of shaders to do this, mainly a vertex shader and a fragment shader.</li>
          |            <li>One series of shaders is called a “program”.</li>
          |            <li>Shaders are written in a language called GLSL.</li>
          |            <li>Vertex shaders compute vertex positions, fragment shaders compute colors for each pixel.</li>
          |            <li>Buffers are arrays of binary data uploaded to the GPU through a shader.</li>
          |            <li>Attributes are used to specify how to pull data out of your buffers and provide them to the shader.</li>
          |            <li>Vertex array objects (VAOs) are used to hold the state of attributes.</li>
          |            <li>Uniforms are global variables you set before executing your shader program.</li>
          |            <li>Textures are arrays of data you can randomly access in your shader program.</li>
          |            <li>Varyings are a way for a vertex shader to pass data onwards to a fragment shader.</li>
          |            <li>Fragment shader output is defined with “out”.</li>
          |            <li>To pass data into the vertex shader you must find the attribute location for a variable in GLSL, then create a buffer, then bind the buffer, then do the same for a vertex array.</li>
          |            <li>Different primitives are used for drawing, but the most common is gl.TRIANGLES.</li>
          |            <li>“Clip space” is what GLSL understands coordinates to be. It’s always on a plane of -1 to 1 for both x and y axis regardless of height and width. To pass in “normal” vertices you have to convert them to clip space.</li>
          |            <li>Translation, rotation, and scale are types of “transformations”. Order matters for them.</li>
          |            <li>Translation is for moving things. Simply add it to the position: a_position + u_translation</li>
          |            <li>Rotation is for rotating things alone a given axis.
          |        <ul>
          |            <li>Create a new rotated position: rotatedPosition = vec2(a_position.x * u_rotation.y + a_position.y * u_rotation.x, a_position.y * u_rotation.y - a_position.x * u_rotation.x)</li>
          |            <li>Basically you multiply the X coordinate by the Y position on the unit circle, and the Y coordinate by the X position on the unit circle and add them together for the final X.</li>
          |            <li>Multiply the Y by Y on the circle and subtract x times the x on the circle for the final Y.</li>
          |            <li>The points on the unit circle can be looked up using sine and cosine of an angle.</li>
          |        </ul>
          |        <li>Scale is for making things bigger or smaller. Simply multiply it to the position: a_position * u_scale. Negative values flip the geometry.</li>
          |        <li>You can run any transformation with matrix math.
          |        <ul>
          |            <li>
          |                Left column transforms X, next transforms Y, next transforms Z. (Sometimes it goes top to bottom depending on the API).
          |            </li>
          |            <li>Identity matrix is the equivalent of “1” it multiplies all rows by 1 in a matrix
          |                <ul>
          |                    <li>
          |                        [<br>
          |                        1, 0, 0, 0<br>
          |                        0, 1, 0, 0<br>
          |                        0, 0, 1, 0<br>
          |                        0, 0, 0, 1
          |                        <br>]
          |                    </li>
          |                </ul>
          |            </li>
          |            <li>Scaling matrix multiples each row by the scale you want.
          |                <ul>
          |                    <li>
          |                        [<br>
          |                        s1, 0, 0, 0<br>
          |                        0, s2, 0, 0<br>
          |                        0, 0, s3, 0<br>
          |                        0, 0, 0, 1
          |                        <br>]
          |                    </li>
          |                </ul>
          |            </li>
          |            <li>Translation matrix puts values in the last unused column, it’s also used to move the origin of what you’re rotating.
          |                <ul>
          |                    <li>
          |                        [<br>
          |                        1, 0, 0, 0<br>
          |                        0, 1, 0, 0<br>
          |                        0, 0, 1, 0<br>
          |                        t1, t2, t3, 1
          |                        <br>]
          |                    </li>
          |                </ul>
          |            </li>
          |            <li>Rotation has different matrices for the three different axes.
          |                <ul>
          |                    <li>
          |                        X, rotate the other two Y and Z axes (c = cos(angle), s = sin(angle)):
          |                    </li>
          |                    <li>
          |                        [<br>
          |                        1, 0, 0, 0<br>
          |                        0, c, s, 0<br>
          |                        0, -s, c, 0<br>
          |                        0, 0, 0, 1
          |                        <br>]
          |                    </li>
          |                    <li>
          |                        Y, rotate X and Z:
          |                    </li>
          |                    <li>
          |                        [<br>
          |                        c, 0, -s, 0<br>
          |                        0, 1, 0, 0<br>
          |                        s, 0, c, 0<br>
          |                        0, 0, 0, 1
          |                        <br>]
          |                    </li>
          |                    <li>
          |                        Z, rotate X and Y:
          |                    </li>
          |                    <li>
          |                        [<br>
          |                        c, -s, 0, 0<br>
          |                        -s, c, 0, 0<br>
          |                        0, 0, 1, 0<br>
          |                        0, 0, 0, 1
          |                        <br>]
          |                    </li>
          |                </ul>
          |            </li>
          |        </ul>
          |        </li>
          |        <li>Matrices can be multiplied together, allowing us to do all transformations at once in whatever order we want.</li>
          |        <li>Typical order is translation -> rotation -> scale.</li>
          |        <li>A “projection” matrix can be made to go from clip space to pixels and back. Involves 3 scale matrices and a translation matrix.</li></ul>
          |    </body>
          |</html>
          |""".stripMargin.replaceAll(" +", "")
    }

    "should display the 'Building a 3D Renderer From Scratch Part 1: TGA Images' blog post" in {
      val controller = new BlogController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.rendererTgaImages().apply(FakeRequest())
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
          |<body>
          |<h1>
          |    Building a 3D Renderer From Scratch
          |</h1>
          |<h2>
          |    Part 1: TGA Images
          |</h2>
          |<h3>
          |    Intro
          |</h3>
          |<p>
          |    &emsp;&emsp;The renderer is the foundation upon which graphics programming is built. It follows then that in order to understand graphics programming on a fundamental level one must have at least a cursory understanding
          |    of rendering and the rendering pipeline. The greater your understanding of the central concepts of rendering, the easier time you'll have with graphics programming in general. The rendering pipeline (which I
          |    talk about at a high level in detail in my graphics programming history blog post <a href="/blog/graphicshistory">here</a>), refers to the steps or instructions that must be executed by a graphics program in
          |    order to convert a scene defined in 3D euclidean space to a 2D screen made of pixels. Every single graphics program, from those run in video games and movies to those used in computer aided design, performs
          |    rendering in order to display the scene to the user.
          |</p>
          |<p>
          |    &emsp;&emsp;So where do we begin? How do we start to understand rendering? By building something ourselves of course! Normally the rendering phase of a graphics program is done for you by whatever framework you happen
          |    to be using, be it OpenGL, WebGL, Vulkan, or some other abstraction. For my purposes however, I'm going to forego those niceties in favor of building my own rudimentary graphics framework that can handle rendering.
          |    Luckily for me, there are lots of other people who've had this same idea before, and upon whose shoulders I can stand. One such person is Dmitry V. Sokolov (or simply ssloy as he's known on github). He wrote a
          |    series of tutorials which I'll be following along with. They can be found <a href="https://github.com/ssloy/tinyrenderer/wiki/Lesson-0-getting-started">here</a>.
          |</p>
          |<p>
          |    &emsp;&emsp;You might be asking yourself at this point, "what's the point of me reading this blog if there's already a different, more qualified person who wrote a blog about this subject? It even includes a
          |    tutorial!". I don't know. But I do know why I'm <i>writing</i> this blog. I'm writing this blog so that I can A) write notes and dump the contents of my brain as I go so that I'm less likely to forget things,
          |    B) write the source code for my own renderer without cheating and looking ahead at his source code too much so that you can see my process, and most importantly C) pressure myself socially into actually getting
          |    through an entire project. Maybe you'll find this content useful or entertaining. Maybe seeing my process will help you develop your own, or will inspire you to do some project you've been meaning to get around
          |    to. Or maybe watching me struggle through something will provide you with some kind of catharsis. Or maybe you already closed this tab and won't ever read this sentence. That's okay, it wasn't a particularly nice
          |    sentence anyway. I mean, who starts with a conjunction twice in a row? Yuck. At least I'm not one of those conjuction-starters that need to put a comma after it every time as if what I'm saying is so important that
          |    it needs the emphasis. Or, maybe I am? Regardless I'm going to keep going with this blog post so that I can reap the benefits of creation and consumption at the same time.
          |</p>
          |<p>
          |    &emsp;&emsp;Anyway onto the actual substance of this post. My starting point with rendering is the starting point I find myself gravitating towards for most programming: inputs and outputs. What goes into a renderer,
          |    and what comes out? Per the definition earlier, the input to a renderer is a definition of a scene in 3D. This means some kind of specification of the geometry involved with points and vertices and lines and triangles.
          |    That's all well and good, but I actually would rather start with the output of a renderer, which is a 2D image. Typically a graphics program will output what's called a "rendered image file format". These formats
          |    include OpenEXR, SVG, PNG, JPG, JPEG, and TGA files. The framework will then string these images together as they render into individual frames, creating an animation effect where
          |    you can define changes in geometry over time. I'm just going to start with static images though and not worry about building full executables. TGA (often referred to as "Targa")
          |    was the native format for TARGA and VISTA boards, which were the first graphics cards for IBM-compatible PCs. The format is perfect for learning how rendered image formats work
          |    because of its simplistic structure, so I'll be focusing on that format throughout this blog. Also because Sokolov's tutorial tells me to. I don't know who he is but I already
          |    trust this fantastic eastern European man with my life.
          |</p>
          |<h3>
          |    The Specification of the TGA Format
          |</h3>
          |<p>
          |    &emsp;&emsp;TGA files contain two main pieces: a header and a body. The header defines some stuff that the operating system can use to quickly determine the size of the file, like the bitmap size and amount of
          |    color information. The body contains the actual color information on a per-pixel basis. My fellow programmer and new internet best friend (don't tell Mr. Sokolov) Paul Bourke has a set of <i>really</i>
          |    good definitions for a lot of file formats, including TGA, that you can find <a href="http://paulbourke.net/dataformats/">here</a>. The main gist of it though, for those who don't want to click the link, is
          |    that the header of a TGA file contains the following fields:
          |</p>
          |    <pre><code>
          |        typedef struct
          |        {
          |            char  idLength;
          |            char  colorMapType;
          |            char  datatypeCode;
          |            short int colorMapOrigin;
          |            short int colorMapLength;
          |            char  colorMapDepth;
          |            short int xOrigin;
          |            short int yOrigin;
          |            short width;
          |            short height;
          |            char  bitsPerPixel;
          |            char  imageDescriptor;
          |        } HEADER;
          |    </code></pre>
          |<p>
          |    &emsp;&emsp;Going through these one-by-one we have the ID Length field, which defines the length of the colormap string in the body of the file. The color map type, which defines whether or not it's a colormapped
          |    image. For our purposes we won't ever use a colormapped image so you can pretty much ignore this field but if you want to know more click <a href="https://en.wikipedia.org/wiki/Color_mapping">here</a>. The image
          |    datatype code which defines what kind of image it is, which for our purposes will always be RGB (others include black and white, colormapped, etc...). The color map specification which includes origin, length and
          |    depth. The image specification which includes the x and y origins, the width and height of the image, how many bits per pixel, and an optional image descriptor which we can ignore. The body of the file will simply
          |    follow this format:
          |</p>
          |    <pre><code>
          |        typedef struct
          |        {
          |            char r, g, b, a;
          |        } PIXEL;
          |
          |        typedef struct
          |        {
          |            PIXEL body[idLength];
          |        } BODY;
          |    </code></pre>
          |<p>
          |    &emsp;&emsp;Basically it's just an array of pixels, each of which has a red value, a green value, a blue value, and an alpha value. That's all there is to it! Now let's dive into some coding!
          |</p>
          |<h3>Creating a Simple Class for TGA Interaction</h3>
          |<p>
          |    &emsp;&emsp;All the code for this project will be C++, by the way. Anyway let's start by defining a header for a class to deal with TGA images.
          |</p>
          |<pre><code>
          |        class TgaImage
          |        {
          |          protected:
          |            unsigned char* data;
          |            int width;
          |            int height;
          |            int bytesPerPixel;
          |
          |          public:
          |            enum Format
          |            {
          |                GRAYSCALE = 1,
          |                RGB = 3,
          |                RGBA = 4
          |            };
          |
          |            TgaImage();
          |            TgaImage(int w, int h, int bpp);
          |            TgaImage(const TgaImage &img);
          |            ~TgaImage();
          |
          |            TgaImage &operator =(const TgaImage &img);
          |        }
          |</code></pre>
          |<p>
          |    &emsp;&emsp;The basic fields for the header are width, height, and bytesPerPixel. Our data field represents the body of the TGA image. There's
          |    a format enum so that we can optionally extend this class for other uses later, a few constructors, and a destructor. Finally we'll overload the
          |    assignment operator so that we don't have to use <code>new</code> every time we want to create a new one from an existing one. Let's go ahead and
          |    implement this header.
          |</p>
          |<pre><code>
          |        TgaImage::TgaImage() : data(nullptr), width(0), height(0), bytesPerPixel(0)
          |        {}
          |
          |        TgaImage::TgaImage(int w, int h, int bpp) : data(nullptr), width(w), height(h), bytesPerPixel(bpp)
          |        {
          |            unsigned long numberOfBytes = width * height * bytesPerPixel;
          |            data = new unsigned char[numberOfBytes];
          |            memset(data, 0, numberOfBytes);
          |        }
          |
          |        TgaImage::TgaImage(const TgaImage &img)
          |        {
          |            width = img.width;
          |            height = img.height;
          |            bytesPerPixel = img.bytesPerPixel;
          |            unsigned long numberOfBytes = width * height * bytesPerPixel;
          |            data = new unsigned char[numberOfBytes];
          |            memcpy(data, img.data, numberOfBytes);
          |        }
          |
          |        TgaImage::~TgaImage()
          |        {
          |            delete[] data;
          |        }
          |
          |        TgaImage &TgaImage::operator=(const TgaImage &img)
          |        {
          |            if (this != &img)
          |            {
          |                delete[] data;
          |                width = img.width;
          |                height = img.height;
          |                bytesPerPixel = img.bytesPerPixel;
          |                unsigned long numberOfBytes = width * height * bytesPerPixel;
          |                data = new unsigned char[numberOfBytes];
          |                memcpy(data, img.data, numberOfBytes);
          |            }
          |            return *this;
          |        }
          |</code></pre>
          |<p>
          |    &emsp;&emsp;Overall it's pretty self-explanatory. Just allocating and deallocating memory appropriately. Next we'll define and implement a few
          |    getters and setters.
          |</p>
          |<pre><code>
          |        int getWidth();
          |        int getHeight();
          |        int getBytesPerPixel();
          |        unsigned char* buffer();
          |        void clear();
          |
          |        int TgaImage::getWidth()
          |        {
          |            return width;
          |        }
          |
          |        int TgaImage::getHeight()
          |        {
          |            return height;
          |        }
          |
          |        int TgaImage::getBytesPerPixel()
          |        {
          |            return bytesPerPixel;
          |        }
          |
          |        unsigned char* TgaImage::buffer()
          |        {
          |            return data;
          |        }
          |
          |        void TgaImage::clear()
          |        {
          |            memset((void*) data, 0, width * height * bytesPerPixel);
          |        }
          |</code></pre>
          |<p>
          |    &emsp;&emsp;Again pretty obvious, the only slightly tricky one being <code>clear()</code>, which just clears and reallocates the data array. Now
          |    we can get into the meat of this class by defining the methods that'll do the actual interaction with the TGA files.
          |</p>
          |<pre><code>
          |        bool flipHorizontally();
          |        bool flipVertically();
          |        bool readTgaFile(const char* filename);
          |        bool writeTgaFile(const char* filename);
          |</code></pre>
          |<p>
          |    &emsp;&emsp;Let's go through these implementations one-by-one, starting with <code>flipHorizontally</code> and <code>flipVertically</code>.
          |</p>
          |<pre><code>
          |        bool TgaImage::flipHorizontally()
          |        {
          |            if (!data)
          |            {
          |                return false;
          |            }
          |            int half = width >> 1u; // bit shift by 1 to divide by 2 without having to handle rounding manually.
          |            for (int i = 0; i < half; i++)
          |            {
          |                for (int j = 0; j < height; j++)
          |                {
          |                    // Loop through each row, and flip the bits on the Y axis
          |                    // going inwards from the outside.
          |                    // [p1, p2, p3, p4,
          |                    // p5, p6, p7, p8]
          |                    // with width 4 and height 2 becomes
          |                    // [p4, p3, p2, p1,
          |                    //  p8, p7, p6, p5]
          |                    TgaColor c1 = get(i, j);
          |                    TgaColor c2 = get(width - 1 - i, j);
          |                    set(i, j, c2);
          |                    set(width - 1 -i, j, c1);
          |                }
          |            }
          |            return true;
          |        }
          |
          |        bool TgaImage::flipVertically()
          |        {
          |            if (!data)
          |            {
          |                return false;
          |            }
          |            unsigned long bytesPerLine = width * bytesPerPixel;
          |            auto* line = new unsigned char[bytesPerLine];
          |            int half = height >> 1u; // bit shift by 1 to divide by 2 without having to handle rounding manually.
          |            for (int j = 0; j < half; j++)
          |            {
          |                // Flip on a per-row basis, going from outside in.
          |                // [p1, p2, p3, p4,
          |                // p5, p6, p7, p8]
          |                // with width 4 and height 2 becomes
          |                // [p5, p6, p7, p8,
          |                //  p1, p2, p3, p4]
          |                // Use line as a temporary storage so we can swap the lines.
          |                unsigned long l1 = j * bytesPerLine;
          |                unsigned long l2 = (height - 1 - j) * bytesPerLine;
          |                memmove((void*) line, (void*) (data + l1), bytesPerLine);
          |                memmove((void*) (data + l1), (void*) (data + l2), bytesPerLine);
          |                memmove((void*) (data + l2), (void*) line, bytesPerLine);
          |            }
          |            delete [] line;
          |            return true;
          |        }
          |</code></pre>
          |<p>
          |    &emsp;&emsp;You may have noticed that the <code>flipHorizontally</code> function calls two methods that haven't been defined, <code>get</code>
          |    and <code>set</code>. These two methods will need to get the color values for a pixel at a given X, Y location and set the color values for a pixel
          |    at a given X, Y location respectively. Let's define and implement those methods.
          |</p>
          |<pre><code>
          |        TgaColor get(int x, int y);
          |        bool set(int x, int y, TgaColor c);
          |
          |        TgaColor TgaImage::get(int x, int y)
          |        {
          |            if (!data || x < 0 || y < 0 || x >= width || y >= height)
          |            {
          |                return TgaColor();
          |            }
          |            return TgaColor(data + (x + y * width) * bytesPerPixel, bytesPerPixel);
          |        }
          |
          |        bool TgaImage::set(int x, int y, TgaColor c)
          |        {
          |            if (!data || x < 0 || y < 0 || x >= width || y >= height)
          |            {
          |                return false;
          |            }
          |            memcpy(data + (x + y * width) * bytesPerPixel, c.raw, bytesPerPixel);
          |            return true;
          |        }
          |</code></pre>
          |<p>
          |    &emsp;&emsp;Both methods just run some sanity checking making sure the coordinates exist in our plane, then either return or set the color values
          |    at a given location. We used a new data structure <code>TgaColor</code> to represent the RGBA values. Here's the definition of that.
          |</p>
          |<pre><code>
          |        struct TgaColor
          |        {
          |            union
          |            {
          |                struct
          |                {
          |                    unsigned char r, g, b, a;
          |                };
          |                unsigned char raw[4];
          |                unsigned int val;
          |            };
          |
          |            int bytesPerPixel;
          |        }
          |</code></pre>
          |<p>
          |    &emsp;&emsp;Note the union field. That's to make it so you can either input 4 defined chars, 4 raw chars, or simply define a single int value all in the same field without allocating more memory than necessary. Now just like
          |    with most classes we'll want a default constructor;
          |</p>
          |<pre><code>
          |        TgaColor() : val(0), bytesPerPixel(1) {}
          |</code></pre>
          |<p>
          |    &emsp;&emsp;And now let's make constructors for our 3 different types in the union:
          |</p>
          |<pre><code>
          |        TgaColor(unsigned char R, unsigned char G, unsigned char B, unsigned char A) : r(R), g(G), b(B), a(A), bytesPerPixel(4) {}
          |
          |        TgaColor(int v, int bpp) : val(v), bytesPerPixel(bpp) {}
          |
          |        TgaColor(const unsigned char* p, int bpp) : val(0), bytesPerPixel(bpp)
          |        {
          |            for (int i = 0; i < bpp; i++)
          |            {
          |                raw[i] = p[i];
          |            }
          |        }
          |</code></pre>
          |<p>
          |    &emsp;&emsp;In the first constructor it assumes we use the strictly defined RGBA, which means we need 4 bytes per pixel to handle the 4 channels. The second one takes the arbitrary integer value and allows the user to define
          |    the bits per pixel (which is mostly used to make a null image), and the last one will loop through an arbitrary array of characters with a user-defined bits per pixel which allows for just RGB if someone feels
          |    like omitting the A. Finally let's overload the assignment operator so that the user doesn't have to <code>new</code> up their instance of this class if they don't want and can instead set it equal to an
          |    already-created instance.
          |</p>
          |<pre><code>
          |        TgaColor & operator =(const TgaColor &c)
          |        {
          |            if (this != &c)
          |            {
          |                bytesPerPixel = c.bytesPerPixel;
          |                val = c.val;
          |            }
          |            return *this;
          |        }
          |</code></pre>
          |<p>&emsp;&emsp;Now that we've got all that sorted for <code>flipHorizontally</code> and <code>flipVertically</code> we can move on to <code>readTgaFile</code>.</p>
          |<pre><code>
          |        bool TgaImage::readTgaFile(const char *filename)
          |        {
          |            // Make sure data is empty before we start.
          |            delete[] data;
          |            data = nullptr;
          |
          |            // First, we open the file.
          |            std::ifstream in;
          |            in.open(filename, std::ios::binary);
          |            if (!in.is_open())
          |            {
          |                std::cerr << "can't open file " << filename << "\n";
          |                in.close();
          |                return false;
          |            }
          |
          |            // Handle the header and set the appropriate fields on our class as we read them in.
          |            TgaHeader header;
          |            in.read((char *) &header, sizeof(header));
          |            if (!in.good())
          |            {
          |                in.close();
          |                std::cerr << "an error occured while reading the header\n";
          |                return false;
          |            }
          |            width = header.width;
          |            height = header.height;
          |            bytesPerPixel = header.bitsPerPixel >> 3;
          |            if (width <= 0 || height <= 0 || (bytesPerPixel != GRAYSCALE && bytesPerPixel != RGB && bytesPerPixel != RGBA))
          |            {
          |                in.close();
          |                std::cerr << "bad bpp (or width/height) value\n";
          |                return false;
          |            }
          |
          |            // Handle the body as we read data in and allocate memory accordingly.
          |            unsigned long numberOfBytes = bytesPerPixel * width * height;
          |            data = new unsigned char[numberOfBytes];
          |
          |            // 2 and 3 are normal RGB images and 10 and 11 use run length encoding.
          |            if (header.datatypeCode == 2 || header.datatypeCode == 3)
          |            {
          |                in.read((char *) data, numberOfBytes);
          |                if (!in.good())
          |                {
          |                    in.close();
          |                    std::cerr << "an error occured while reading the data\n";
          |                    return false;
          |                }
          |            }
          |            else if (10 == header.datatypeCode || 11 == header.datatypeCode)
          |            {
          |                if (!loadRleData(in))
          |                {
          |                    in.close();
          |                    std::cerr << "an error occured while reading the data\n";
          |                    return false;
          |                }
          |            }
          |            else
          |            {
          |                in.close();
          |                std::cerr << "unknown file format " << (int) header.datatypeCode << "\n";
          |                return false;
          |            }
          |            if (!(header.imageDescriptor & 0x20))
          |            {
          |                flipVertically();
          |            }
          |            if (header.imageDescriptor & 0x10)
          |            {
          |                flipHorizontally();
          |            }
          |            std::cerr << width << "x" << height << "/" << bytesPerPixel * 8 << "\n";
          |            in.close();
          |            return true;
          |        }
          |</code></pre>
          |<p>
          |    &emsp;&emsp;This again uses a new data structure. It's called TgaHeader and it just represents the header fields we talked about earlier. Here's a
          |    quick definition for that structure.
          |</p>
          |<pre><code>
          |        struct TgaHeader
          |        {
          |            char idLength;
          |            char colorMapType;
          |            char datatypeCode;
          |            short colorMapOrigin;
          |            short colorMapLength;
          |            char colorMapDepth;
          |            short xOrigin;
          |            short yOrigin;
          |            short width;
          |            short height;
          |            char bitsPerPixel;
          |            char imageDescriptor;
          |        };
          |</code></pre>
          |<p>
          |    &emsp;&emsp;Now we can read TGA files into memory! But what about writing them to disk? Here's the implementation for that.
          |</p>
          |<pre><code>
          |        bool TgaImage::writeTgaFile(const char *filename, bool rle)
          |        {
          |            unsigned char developer_area_ref[4] = {0, 0, 0, 0};
          |            unsigned char extension_area_ref[4] = {0, 0, 0, 0};
          |            unsigned char footer[18] = {'T', 'R', 'U', 'E', 'V', 'I', 'S', 'I', 'O', 'N', '-', 'X', 'F', 'I', 'L', 'E', '.',
          |                                        '\0'};
          |            std::ofstream out;
          |            out.open(filename, std::ios::binary);
          |            if (!out.is_open())
          |            {
          |                std::cerr << "can't open file " << filename << "\n";
          |                out.close();
          |                return false;
          |            }
          |            TgaHeader header;
          |            memset((void *) &header, 0, sizeof(header));
          |            header.bitsPerPixel = bytesPerPixel << 3;
          |            header.width = width;
          |            header.height = height;
          |            header.datatypeCode = (bytesPerPixel == GRAYSCALE ? (rle ? 11 : 3) : (rle ? 10 : 2));
          |            header.imageDescriptor = 0x20; // top-left origin
          |            out.write((char *) &header, sizeof(header));
          |            if (!out.good())
          |            {
          |                out.close();
          |                std::cerr << "can't dump the tga file\n";
          |                return false;
          |            }
          |
          |            // Handle run length encoding. I don't want to go into depth about this
          |            // for this post because it's supposed to be about rendering not encoding.
          |            if (!rle)
          |            {
          |                out.write((char *) data, width * height * bytesPerPixel);
          |                if (!out.good())
          |                {
          |                    std::cerr << "can't unload raw data\n";
          |                    out.close();
          |                    return false;
          |                }
          |            } else
          |            {
          |                if (!unloadRleData(out))
          |                {
          |                    out.close();
          |                    std::cerr << "can't unload rle data\n";
          |                    return false;
          |                }
          |            }
          |            out.write((char *) developer_area_ref, sizeof(developer_area_ref));
          |            if (!out.good())
          |            {
          |                std::cerr << "can't dump the tga file\n";
          |                out.close();
          |                return false;
          |            }
          |            out.write((char *) extension_area_ref, sizeof(extension_area_ref));
          |            if (!out.good())
          |            {
          |                std::cerr << "can't dump the tga file\n";
          |                out.close();
          |                return false;
          |            }
          |            out.write((char *) footer, sizeof(footer));
          |            if (!out.good())
          |            {
          |                std::cerr << "can't dump the tga file\n";
          |                out.close();
          |                return false;
          |            }
          |            out.close();
          |            return true;
          |        }
          |</code></pre>
          |<p>
          |    &emsp;&emsp;Huzzah! We're now able to handle TGA image interaction for both inputting and outputting files. Now it's time to create a main program
          |    that will test all our functionality and create our very first rendered image!
          |</p>
          |<pre><code>
          |        #include "TgaImage.h"
          |
          |        const TgaColor white = TgaColor(255, 255, 255, 255);
          |        const TgaColor red   = TgaColor(255, 0, 0, 255);
          |
          |        int main(int argc, char** argv) {
          |            TgaImage image(100, 100, TgaImage::RGB);
          |            // Define a small, red rectangle near the center of the screen.
          |            image.set(52, 41, red);
          |            image.set(53, 41, red);
          |            image.set(54, 41, red);
          |            image.set(55, 41, red);
          |            image.set(56, 41, red);
          |            image.set(57, 41, red);
          |            image.set(52, 42, red);
          |            image.set(53, 42, red);
          |            image.set(54, 42, red);
          |            image.set(55, 42, red);
          |            image.set(56, 42, red);
          |            image.set(57, 42, red);
          |            image.set(52, 43, red);
          |            image.set(53, 43, red);
          |            image.set(54, 43, red);
          |            image.set(55, 43, red);
          |            image.set(56, 43, red);
          |            image.set(57, 43, red);
          |            image.flipVertically(); // I want to have the origin at the left bottom corner of the image.
          |            image.writeTgaFile("output.tga");
          |            return 0;
          |        }
          |</code></pre>
          |<img src="/assets/images/firstrenderedimage.png" height="600" width="800" alt="Placeholder image">
          |<p>&emsp;&emsp;Tada! We did it! We made a rendered image! Look at that red rectangle. Isn't it glorious? Next time we'll be discussing defining geometry and drawing lines.</p>
          |<p>&emsp;&emsp;Code for this blog post can be found <a href="https://github.com/blipson/simplerenderer/tree/tga-files">here</a>.</p>
          |<p>&emsp;&emsp;Click <a href="/blog/renderer/drawinglines">here</a> to go to part 2.</p>
          |</body>
          |</html>
          |""".stripMargin.replaceAll(" +", "")
    }

    "should display the 'Building a 3D Renderer From Scratch Part 2: Drawing Lines' blog post" in {
      val controller = new BlogController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.rendererDrawingLines().apply(FakeRequest())
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
          |<body>
          |<h1>
          |    Building a 3D Renderer From Scratch
          |</h1>
          |<h2>
          |    Part 2: Drawing Lines
          |</h2>
          |<h3>
          |    Intro
          |</h3>
          |<p>
          |    &emsp;&emsp;After completing <a href="/blog/renderer/tgaimages">part 1</a> of this series of blog posts, you should be able to
          |    define the color for each pixel within a TGA image and then output that image from your program. So what's next? Well, as per my
          |    <a href="/blog/graphicsnotes">graphics programming notes</a> that I like to keep online, all that renderers really do is draw points,
          |    draw lines, and fill in triangles between those lines. We can do the first part, each pixel is a point that we can "draw" by changing
          |    the color of that point. So it follows that next we'd want to be able to string those points together as lines.
          |</p>
          |<p>
          |    &emsp;&emsp;Last time I stated that it's useful to think about programs like renderers in terms of inputs and outputs. We focused on
          |    the output, TGA images, but what about the input? A renderer must take in some specification of geometry before deciding how to color
          |    the pixels on the screen. This usually comes in the form of a subset of "assets" (static files used as input to a program). Specifically
          |    for our purposes, as 3D object files. They can come in the form of STL, OBJ, FBX, COLLADA, 3DS, etc... files. After we go through drawing
          |    lines we'll discuss how we can interact with these geometry defintion file formats in our C++ program, specifically we'll be using OBJ files
          |    because they use one of the simplest and most ubiquitous formats.
          |</p>
          |<h3>
          |    The Linear Equation
          |</h3>
          |<p>
          |    &emsp;&emsp;If you had the distinct privilege of going to middle school and attending math class you may remember learning how to graph
          |    linear equations. If not, you can find a nice overview <a href="https://www.mathsisfun.com/algebra/linear-equations.html">here</a>. Basically
          |    the most common way to write an equation of a line looks like this:
          |</p>
          |<p>
          |    <img src="/assets/images/linearequation.svg"  alt=""/>
          |</p>
          |<p>
          |    &emsp;&emsp;Where x and y represent any given point along the line, m represents the slope of the line (or how steep the line is), and y<sub>0</sub>
          |    represents the value of y when x = 0 (otherwise referred to as the y-intercept because that's the point at which the line intercepts the
          |    y axis on a graph). Here are a few good examples of lines with their equations that I lifted from Wikipedia:
          |</p>
          |<p>
          |    <img src="/assets/images/linearfunctiongraph.png"  width="250"  alt=""/>
          |</p>
          |<p>
          |    &emsp;&emsp;So how do we represent this as C++ code? Well you notice that lines are a lot like how walking my dog feels in Winter in that they go on
          |    forever. The two in the picture from Wikipedia go off the screen and onwards to infinity in both directions. Obviously our computer won't be
          |    able to accurately represent that, so we need a starting and an ending point. Graphing calculators and other such devices typically just use
          |    the boundaries of the screen as start and end points, but since we've already written a program to define points in space we can just have
          |    those be user inputs. So the specification for our function would look like this:
          |</p>
          |<pre><code>
          |        void line(int x0, int y0, int x1, int y1, TgaImage &image, const TgaColor &color)
          |</code></pre>
          |<p>
          |    &emsp;&emsp;Once we have the start point and the end point specified, we can then determine how far the line has risen or fallen (or change in
          |    overall Y position (dy)), and how far the line has run (or change in overall X position (dx)). Once we know dy and dx, it's a simple ratio of
          |    dy/dx to determine the slope. We could use a ratio of run/rise (or dx/dy) if we wanted, but algebra is typically structured around rise/run (dy/dx)
          |    because it represents the number more nicely to our human brains. With rise/run, when the curve gets steeper, the number gets bigger instead of
          |    smaller, which seems to make more intuitive sense to most people. Anyway let's represent our slope calculation in code.
          |</p>
          |<pre><code>
          |        int dx = x1 - x0;
          |        int dy = y1 - y0;
          |        float m = (float) dy / (float) dx;
          |</code></pre>
          |<p>
          |    &emsp;&emsp;(Note: we're using (float) typecasting because we need the precision floating point numbers provide. If a line has a slope of 3/4 we
          |    need that to be equal to 0.75, not 1). Now we only need to calculate one more value, the value of the y-intercept, before we've fully solved the
          |    line equation between our two user-inputted points. Since we have all the other values, we can rearrange the y = mx + y<sub>0</sub> to calculate
          |    the y<sub>0</sub> like this (our code will use the variable "b" to represent y<sub>0</sub> because C++ doesn't allow for subscript in variable
          |    names):
          |</p>
          |<pre><code>
          |        y = (m * x) + b
          |        y - b = (m * x) // subtract b from both sides
          |        -b = (m * x) - y // subtract y from both sides
          |        -b = -y + (m * x) // flip right side around
          |        b = y - (m * x) // negate both sides
          |</code></pre>
          |<p>
          |    &emsp;&emsp;Now we can calculate any value within the equation! This means that we can draw our full line. All we have to do is start at our
          |    starting point and iterate through the X coordinate until we've reached our ending point, calculating Y as we go. We could do it the other way,
          |    iterating through Y and calculating X, but this way seemed simpler to me.
          |</p>
          |<pre><code>
          |        for (int x = x0; x <= x1; x++)
          |        {
          |            int y = (m * x) + b;
          |            image.set(x, y, color);
          |        }
          |</code></pre>
          |<p>
          |    &emsp;&emsp;Finally, we can call our line function and we'll have our first line! This example goes from (13, 20) to (80, 40).
          |</p>
          |<pre><code>
          |        line(13, 20, 80, 40, image, white);
          |</code></pre>
          |<p>
          |    <img src="/assets/images/firstline.png" width="250"  alt=""/>
          |</p>
          |<p>
          |    &emsp;&emsp;Neat! But there are still some problems with this code. See if you can sort out a few of them yourself before reading on! Ready?
          |    Cool, me too. Firstly, the choice of constant for our iterator matters greatly. If we change <code>for (int x = x0; x <= x1; x++)</code> to be
          |    <code>for (int x = x0; x <= x1; x += 10)</code> for example, changing the constant from 1 to 10, we get:
          |</p>
          |<p>
          |    <img src="/assets/images/sadline.png" width="250"  alt=""/>
          |</p>
          |<p>
          |    &emsp;&emsp;That's a pretty sad line. The larger our constant is the less precise our line will be, which can lead to some efficiency problems
          |    down the line (pun intended) that we'll discuss later. Another problem is that this code only allows for lines from bottom left to top right. Imagine if we'd
          |    called it with the arguments in reverse order like <code>line(80, 40, 13, 20, image, white);</code>. Our for loop would never trigger and we'd
          |    just end up with a blank image. A lot of graphics frameworks actually require that you define your lines from bottom left to top right, and
          |    don't work with input defined the other way around. As such almost all 3D object assets come with lines defined this way, so this bug isn't
          |    actually a huge deal. We can still remedy this though by always getting the minimum x and looping to the maximum x.
          |</p>
          |<pre><code>
          |        int minX = std::min(x0, x1);
          |        int maxX = std::max(x0, x1);
          |        for (int x = minX; x <= maxX; x++)
          |        {
          |            int y = (m * x) + b;
          |            image.set(x, y, color);
          |        }
          |</code></pre>
          |<p>
          |    &emsp;&emsp;In the same vein, what about straight up and down lines where the x values for both points are equal? Right now those types of lines
          |    wouldn't trigger our for loop either, so we have to handle that case. What we can do is switch which axis we're iterating over to the Y axis,
          |    since the X axis necessarily has nothing to iterate over if both X coordinates are equal. As far as calculating the X coordinate for each point,
          |    we can rearrange our y = mx + y<sub>0</sub> formula from earlier to be x = (y - y<sub>0</sub>) / m. You also might notice after drawing a few different lines that steep lines tend
          |    to look a lot worse than non-steep ones. When the height of the line is greater than its width, we'll also want to iterate over the Y axis to make sure we're filling in every point.
          |    After fixing those few bugs, here we have our (relatively) bug-free (albeit very inefficient), rudimentary line-drawing algorithm:
          |</p>
          |<pre><code>
          |        void line(int x0, int y0, int x1, int y1, TgaImage &image, const TgaColor &color)
          |        {
          |            int dx = x1 - x0;
          |            int dy = y1 - y0;
          |            float m = (float) dy / (float) dx;
          |            float b = y1 - (m * x1);
          |
          |            int minX = std::min(x0, x1);
          |            int maxX = std::max(x0, x1);
          |
          |            if (minX == maxX || std::abs(dx) < std::abs(dy))
          |            {
          |                int minY = std::min(y0, y1);
          |                int maxY = std::max(y0, y1);
          |                for (int y = minY; y <= maxY; y++)
          |                {
          |                    int x = (y - b) / m;
          |                    image.set(x, y, color);
          |                }
          |            }
          |            else
          |            {
          |                for (int x = minX; x <= maxX; x++)
          |                {
          |                    int y = (m * x) + b;
          |                    image.set(x, y, color);
          |                }
          |            }
          |        }
          |</code></pre>
          |<h3>
          |    Using Lines to Draw Objects
          |</h3>
          |<h3>
          |    The Specification of the OBJ format
          |</h3>
          |<p>
          |    &emsp;&emsp;Once we've got lines, we can string those lines together to make shapes. It's all coming together now! I mentioned in the intro that we'd
          |    discuss the specification of geometry used as input to our program. The time has come! OBJ (object) files are created by Wavefront's Advanced Visualizer
          |    (more info <a href="https://en.wikipedia.org/wiki/The_Advanced_Visualizer">here</a>) and are the uncompressed, raw version of the geometry specification.
          |    They're highly extensible, very powerful, and can encompass definitions for a lot of advanced graphics stuff. For our purposes we're going to focus on a
          |    narrow subset of simple specifications to just get the bare necessities of our program off the ground. If you're interested in doing a deep dive into everything
          |    OBJ files support you can explore my internet best friend Paul Bourke's <a href="http://paulbourke.net/dataformats/obj/">website</a>.
          |</p>
          |<p>
          |    &emsp;&emsp;The bare minimum of this format is overall very simple. It consists of 3 main sets of information. It has geometric vertices, texture vertices, and vertex normals represented through these formats:
          |</p>
          |<pre><code>
          |        typedef struct
          |        {
          |            char type = "v";
          |            float x, y, z;
          |        } GEOMETRIC_VERTEX;
          |
          |        typedef struct
          |        {
          |            char type = "vt";
          |            float x, y, z;
          |        } TEXTURE_VERTEX;
          |
          |        typedef struct
          |        {
          |            char type = "vn";
          |            float x, y, z;
          |        } VERTEX_NORMAL;
          |</code></pre>
          |<p>
          |    &emsp;&emsp;This portion of the file itself looks like this:
          |</p>
          |<pre><code>
          |        v -0.000581696 -0.734665 -0.623267
          |        v 0.000283538 -1 0.286843
          |        v -0.117277 -0.973564 0.306907
          |        v -0.382144 -0.890788 0.221243
          |        v -0.247144 -0.942602 0.276051
          |        v -0.656078 -0.718512 -0.109025
          |        v -0.609847 -0.786562 0.0198068
          |        ... etc ...
          |
          |        vt  0.532 0.923 0.000
          |        vt  0.535 0.917 0.000
          |        vt  0.542 0.923 0.000
          |        vt  0.541 0.929 0.000
          |        vt  0.521 0.984 0.000
          |        vt  0.521 0.996 0.000
          |        vt  0.505 0.998 0.000
          |        vt  0.500 0.985 0.000
          |        ... etc ...
          |
          |        vn  0.001 0.482 -0.876
          |        vn  -0.001 0.661 0.751
          |        vn  0.136 0.595 0.792
          |        vn  -0.203 0.679 0.706
          |        vn  -0.092 0.474 0.876
          |        vn  -0.184 0.792 0.581
          |        vn  -0.098 0.863 0.495
          |        vn  -0.449 0.820 0.356
          |        ... etc ...
          |</code></pre>
          |<p>
          |    &emsp;&emsp;Geometric vertices are exactly what they sound like. They represent a given point in 3D space. Texture vertices define where to place the texture over a set of vertices, and therefore correspond 1-to-1
          |    with geometric vertices. These are a little bit tricky to grasp at first, but we don't need to worry about them since we aren't using textures at all in this part of the tutorial (stay tuned for that!). Vertex
          |    normals represent a euclidean directional vector associated with a vertex. If you don't know what a vector is, you can simply think of it as a line with a direction. The line can only travel in that direction, and
          |    isn't allowed to go the other way. We'll talk more about them later.  This helps us know in which order to draw the lines given to us (and allows for the side-stepping of that bottom-left to top-right bug we fixed earlier). Finally, there's a
          |    fourth section within the OBJ file format that groups the first three all together into a single data structure called a "face". Faces must be triangles and as such must group 3 and only 3 points from each
          |    of our other 3 data structures. The definition for a face looks like this:
          |</p>
          |<pre><code>
          |        typedef struct
          |        {
          |            typedef struct
          |            {
          |                GEOMETRIC_VERTEX geometricVertex;
          |                TEXTURE_VERTEX textureVertex;
          |                VERTEX_NORMAL vertexNormal;
          |            } FACE_POINT;
          |
          |            char type = "f";
          |            float* vertex1 = new FACE_POINT[3];
          |            float* vertex2 = new FACE_POINT[3];
          |            float* vertex3 = new FACE_POINT[3];
          |        } FACE;
          |</code></pre>
          |<p>
          |    &emsp;&emsp;And the file itself for the faces section looks like:
          |</p>
          |<pre><code>
          |        f 24/1/24 25/2/25 26/3/26
          |        f 24/1/24 26/3/26 23/4/23
          |        f 28/5/28 29/6/29 30/7/30
          |        f 28/5/28 30/7/30 27/8/27
          |        f 32/9/32 33/10/33 34/11/34
          |        f 32/9/32 34/11/34 31/12/31
          |        f 36/13/36 31/12/31 34/11/34
          |        f 36/13/36 34/11/34 35/14/35
          |        ... etc ...
          |</code></pre>
          |<h3>
          |    Creating a class for OBJ Interaction
          |</h3>
          |<p>
          |    &emsp;&emsp;Just like in the last part we'll start by defining a header for our class.
          |</p>
          |<pre><code>
          |        class Model
          |        {
          |          private:
          |            std::vector&lt;Vec3f&gt; verts;
          |            std::vector&lt;std::vector&lt;int&gt;&gt; faces;
          |
          |          public:
          |            Model(const char* filename);
          |
          |            ~Model();
          |
          |            int nVerts();
          |
          |            int nFaces();
          |
          |            Vec3f vert(int i);
          |
          |            std::vector&lt;int&gt; face(int i);
          |        };
          |</code></pre>
          |<p>
          |    &emsp;&emsp;Okay so a few a things about this code. In the private section we've got two fields <code>verts</code> and <code>faces</code> to represent the list of vertices (including geometric, textured, and normalized) and the list
          |    of faces respectively. In the public section we have our constructor which will take in a file name as input and create our Model from that, a destructor which will deallocate memory appropriately, and
          |    some getters. <code>nVerts()</code> gives the total number of all vertices, <code>nFaces()</code> gives the total number of all faces, <code>vert(int i)</code> will give you a vertex at a particular position, and
          |    <code>face(int i)</code> will give you a face at a particular position. Now you may have noticed two completely new data classes that we haven't discussed before: <code>std::vector</code> and <code>Vec3f</code>.
          |    <code>std::vector</code> is from the standard library of C++. If you remember our definition of a euclidean vector from earlier then you'll know that it's a line with a direction. Well a vector can also be
          |    represented as a list of points. In fact, you can do the same thing with just a list of ordered numbers and it's still referred to as a vector. In set theory terms it's defined as a member of the set S<sup>n</sup>
          |    which is an ordered sequence of values in a specific set. Even though they're often two or three dimensional, in pure mathematics a vector is just any list of ordered numbers. That's what's meant by C++'s vector
          |    class. Essentially it's a dynamic array that allows you to add or remove elements at will. <code>Vec3f</code> on the other hand is a data structure that I defined. Let's look at that definition:
          |</p>
          |<pre><code>
          |        template&lt;class t&gt;
          |        struct vec3
          |        {
          |            union
          |            {
          |                struct
          |                {
          |                    t x, y, z;
          |                };
          |                struct
          |                {
          |                    t iVert, iuv, iNorm;
          |                };
          |                t raw[3];
          |            };
          |
          |            vec3() : x(0), y(0), z(0)
          |            {
          |            }
          |
          |            vec3(t x, t y, t z) : x(x), y(y), z(z)
          |            {
          |            }
          |
          |            inline vec3&lt;t&gt; operator^(const vec3&lt;t&gt; &v) const
          |            {
          |                return vec3&lt;t&gt;(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
          |            }
          |
          |            inline vec3&lt;t&gt; operator+(const vec3&lt;t&gt; &v) const
          |            {
          |                return vec3&lt;t&gt;(x + v.x, y + v.y, z + v.z);
          |            }
          |
          |            inline vec3&lt;t&gt; operator-(const vec3&lt;t&gt; &v) const
          |            {
          |                return vec3&lt;t&gt;(x - v.x, y - v.y, z - v.z);
          |            }
          |
          |            inline vec3&lt;t&gt; operator*(float f) const
          |            {
          |                return vec3&lt;t&gt;(x * f, y * f, z * f);
          |            }
          |
          |            inline t operator*(const vec3&lt;t&gt; &v) const
          |            {
          |                return x * v.x + y * v.y + z * v.z;
          |            }
          |
          |            float norm() const
          |            {
          |                return std::sqrt(x * x + y * y + z * z);
          |            }
          |
          |            vec3&lt;t&gt; &normalize(t l = 1)
          |            {
          |                *this = (*this) * (l / norm());
          |                return *this;
          |            }
          |
          |            template&lt;class&gt;
          |            friend std::ostream &operator&lt;&lt;(std::ostream &s, vec3&lt;t&gt; &v);
          |        };
          |
          |        typedef vec3&lt;float&gt; Vec3f;
          |        typedef vec3&lt;int&gt; Vec3i;
          |
          |        template&lt;class t&gt;
          |        std::ostream &operator&lt;&lt;(std::ostream &s, vec3&lt;t&gt; &v)
          |        {
          |            s &lt;&lt; "(" &lt;&lt; v.x &lt;&lt; ", " &lt;&lt; v.y &lt;&lt; ", " &lt;&lt; v.z &lt;&lt; ")\n";
          |            return s;
          |        }
          |</code></pre>
          |<p>
          |    &emsp;&emsp;So that's a good little bit of code, and I'm not going to go over it in detail because it's tangential to the topic at hand. All I'll say is that "template&lt;class t&gt;" defines the structure as a
          |    generic template class that can take its own type as an input, that way we can use the same code for vectors with integers or with floating point numbers (as referenced in the type aliases near the bottom), and
          |    that this struct is basically just defining a bunch of <a href="https://mathinsight.org/vector_introduction">vector math</a>. Vector math is really easy once you get the hang of it, so I suggest you do a bit of
          |    research on your own if you aren't comfortable with it already. After going through this code you might be thinking, "But I thought vectors had to have a direction associated with them? Doesn't that mean you
          |    need multiple points? This vec3 struct only stores the x y and z coordinates of one point." Right you are, imaginary reader! When a vector is defined as one point it can be assumed that the direction is moving
          |    away from the origin (0, 0). It's a simple shortcut mathematicians do in order to define points in euclidean space, but still have access to all the nice vector operations that're important for more complex
          |    algebraic operations. For now you can just think of them as "points in space" if you want (though that's not 100% accurate).
          |</p>
          |<p>
          |    &emsp;&emsp;Hopfeully now the header file for our Model class makes sense to you. We've just got an "ordered list" (vector) of "points in space" (vec3f) as our set of vertices, and an ordered list of
          |    ordered lists of integers for our faces. Let's get to the implementation of this header before you die of boredom/confusion from my terrible teaching skills as they relate to mathematics.
          |</p>
          |<pre><code>
          |        int Model::nVerts()
          |        {
          |            return (int) verts.size();
          |        }
          |
          |        int Model::nFaces()
          |        {
          |            return (int) faces.size();
          |        }
          |
          |        std::vector&lt;int&gt; Model::face(int i)
          |        {
          |            return faces[i];
          |        }
          |
          |        Vec3f Model::vert(int i)
          |        {
          |            return verts[i];
          |        }
          |</code></pre>
          |<p>
          |    &emsp;&emsp;Look at how simple that implementation is! That's the power of using vectors! You'll notice I left out the constructor and destructor. That's because I wanted to show you those in isolation. Let's
          |    look at that now! Be sure to read the comments for an overview of what's happening.
          |</p>
          |<pre><code>
          |        Model::Model(const char* filename) : verts(), faces()
          |        {
          |            // open the file and read the lines
          |            std::ifstream in;
          |            in.open(filename, std::ifstream::in);
          |            if (in.fail())
          |            {
          |                return;
          |            }
          |            std::string line;
          |            while (!in.eof())
          |            {
          |                std::getline(in, line);
          |                std::istringstream iss(line);
          |                char trash;
          |                // check if it's a vertex (v, vt, or vn), or a face (f)
          |                if (!line.compare(0, 2, "v "))
          |                {
          |                    iss &gt;&gt; trash;
          |                    Vec3f v;
          |                    for (float &i : v.raw)
          |                    {
          |                        iss &gt;&gt; i;
          |                    }
          |                    verts.push_back(v); // if it's a vertex then read in the raw geomtry data and add it to our vertex list
          |                }
          |                else if (!line.compare(0, 2, "f "))
          |                {
          |                    std::vector&lt;int&gt; f;
          |                    int itrash, idx;
          |                    iss &gt;&gt; trash;
          |                    while (iss &gt;&gt; idx &gt;&gt; trash &gt;&gt; itrash &gt;&gt; trash &gt;&gt; itrash)
          |                    {
          |                        idx--; // in wavefront obj all indices start at 1, not zero
          |                        f.push_back(idx);
          |                    }
          |                    faces.push_back(f); // if it's a face then read in the raw face data, ignoring the slashes between values, and add it to our faces list
          |                }
          |            }
          |            std::cerr &lt;&lt; "# v# " &lt;&lt; verts.size() &lt;&lt; " f# " &lt;&lt; faces.size() &lt;&lt; std::endl;
          |        }
          |
          |        Model::~Model()
          |        = default;
          |</code></pre>
          |<p>
          |    &emsp;&emsp;Bada bing bada boom that's all we need to read in OBJ files! Now we can create a 3D object in memory within our program using something like <code>auto* model = new Model("head.obj");</code>.
          |    All that's left then is to loop through all the faces in our object, then loop through all the vertices contained within that face, and draw them one-by-one by hooking into the line function we made earlier.
          |    They're grouped in loops of length three because, as stated earlier, each face must be a triangle, meaning the three points of the face have to connect to one another.
          |</p>
          |<pre><code>
          |        int main(int argc, char** argv)
          |        {
          |            auto* model = new Model("head.obj");
          |            const int width = 800;
          |            const int height = 800;
          |
          |            TgaImage image(width, height, TgaImage::RGB);
          |            for (int i = 0; i < model->nFaces(); i++)
          |            {
          |                std::vector&lt;int&gt; face = model->face(i);
          |                for (int j = 0; j < 3; j++)
          |                {
          |                    Vec3f v0 = model->vert(face[j]);
          |                    // use the modulo to go back to 0 and connect the triangle when on the last two vertices of the face
          |                    Vec3f v1 = model->vert(face[(j + 1) % 3]);
          |                    // scale the model appropriately to the correct size
          |                    int x0 = (v0.x + 1) * width / 2;
          |                    int y0 = (v0.y + 1) * height / 2;
          |                    int x1 = (v1.x + 1) * width / 2;
          |                    int y1 = (v1.y + 1) * height / 2;
          |                    line(x0, y0, x1, y1, image, white); // draw a line for each pair of vertices in this triangle
          |                }
          |            }
          |
          |            image.flipVertically(); // i want to have the origin at the left bottom corner of the image
          |            image.writeTgaFile("output.tga");
          |            delete model;
          |            return 0;
          |        }
          |</code></pre>
          |<p>
          |    &emsp;&emsp;If we do that with an actual OBJ file (the test one I'm using can be found <a href="https://github.com/blipson/simplerenderer/blob/drawing-lines/head.obj">here</a>,
          |    then the program should fairly accurately draw the wireframe for the triangles that make up the geometry of that 3D object! Here's the final output:
          |</p>
          |<p>
          |    <img src="/assets/images/head.png" width="500" alt="" />
          |</p>
          |<p>
          |    &emsp;&emsp;Look at it! It's beautiful! I hope you learned something about lines and stuff throughout this post. Next time we'll be spicing up our line drawing function by looking at different algorithms
          |    developed by people way smarter than me! Stay tuned
          |</p>
          |<p>
          |    &emsp;&emsp;As always, code for this post is available on <a href="https://github.com/blipson/simplerenderer/tree/drawing-lines">my Github account</a>.
          |</p>
          |</body>
          |</html>
          |""".stripMargin.replaceAll(" +", "")
    }
  }
}
