export default prototype = {

    createTicket(shortDesc, longDesc, resp, gr, cust, stat, sev) {


        return {
            shortDescription : shortDesc,
            longDescription : longDesc,
            userid : resp,
            group : gr,
            customer : cust,
            status : stat,
            severity : sev
        }
    },
    createTicket(shortDesc, longDesc, resp, gr, cust, stat, sev, aff) {


        return {
            shortDescription : shortDesc,
            longDescription : longDesc,
            userid : resp,
            group : gr,
            customer : cust,
            status : stat,
            severity : sev,
            affected : aff
        }
    },


}