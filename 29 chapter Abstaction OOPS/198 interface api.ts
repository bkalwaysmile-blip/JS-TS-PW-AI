// interface. used in API Testing

interface APIReponse{
    statusCode: number;
    body?: string;
    headers?: object;
    responseTime:number;
}

//  GET -> body optional
// POST -> important

const responseDataGET: APIReponse = {
    statusCode:200,
    responseTime: 200
}

const responseDataPOST: APIReponse = {
    statusCode:200,
    body : "{'name':'pramod'}",
    responseTime: 200,
    headers : {}
}