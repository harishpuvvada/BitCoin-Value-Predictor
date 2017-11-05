import requests

URL = 'https://finance.google.com/finance/company_news?q=currency:btc&ei=iTL-WbnAHMOoed_ysvgE&authuser=1'

# def run(**params):
response = requests.get(URL)

print response.content, response.status_code


#run(query="Egypt", month=3, from_day=2, to_day=2, year=13)
