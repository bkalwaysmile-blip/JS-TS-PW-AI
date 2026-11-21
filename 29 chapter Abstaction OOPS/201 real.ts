interface TestConfig {
    browser: string;
    headless: boolean;
    baseURL: string;
    timeout?: number;
    retries?: number;
}

let localConfig: TestConfig = {
    browser: "Chrome",
    headless: true,
    baseURL: "http://localhost:3000"
};


let ciConfig : TestConfig = {
    browser: "Firefox",
    headless: false,
    baseURL: "https://staging.app.com",
    timeout: 10000,
    retries: 3
};


console.log("CI:", ciConfig.browser, "| timeout:", ciConfig.timeout);
console.log("Local:", localConfig.browser, "| timeout:", localConfig.timeout);