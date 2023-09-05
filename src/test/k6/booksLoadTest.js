import http from 'k6/http';
import { sleep } from 'k6';

const endpoint = "http://localhost:9001"

export const options = {
    vus: 3000,
    duration: "100s"
}
export default function () {
    http.get(endpoint + "/books");
    sleep(1);
}
