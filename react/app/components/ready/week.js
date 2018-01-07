import React from 'react';
import Day from './day';

export default class Week extends React.Component {
  constructor(props) {
    super(props);
    this.getNextWeek = this.getNextWeek.bind(this);
    this.getPrevWeek = this.getPrevWeek.bind(this);
    this.getScheduleFromServer = this.getScheduleFromServer.bind(this);
    this.state = {
      offset: 0,
      masterId: this.props.masterId
    };
  }

  getNextWeek() {
    console.log("offset + 1");
    this.setState({offset: this.state.offset + 1});
  }

  getPrevWeek() {
    console.log("offset - 1");
    this.setState({offset: this.state.offset - 1});
  }

  getScheduleFromServer() {
    const sch1 = {
      "0": {
        "service_date": "01-01-2018",
        "workitems": []
      },
      "1": {
        "service_date": "02-01-2018",
        "workitems": []
      },
      "2": {
        "service_date": "03-01-2018",
        "workitems": [
          {
            "id": 2,
            "comment": null,
            "service_date": "03-01-2018",
            "service_time": "10:00",
            "master_id": 1,
            "client_id": 1,
            "procedures": [
              {
                "id": 1,
                "procedure_name": "shellac"
              }
            ],
            "client_detales": {
              "name": "sergii gary slon master",
              "phones": "0962377850",
              "photo": null
            }
          },
          {
            "id": 3,
            "comment": "some comment",
            "service_date": "03-01-2018",
            "service_time": "10:00",
            "master_id": 1,
            "client_id": 1,
            "procedures": [
              {
                "id": 1,
                "procedure_name": "shellac"
              }
            ],
            "client_detales": {
              "name": "sergii gary slon master",
              "phones": "0962377850",
              "photo": null
            }
          }
        ]
      },
      "3": {
        "service_date": "04-01-2018",
        "workitems": [
          {
            "id": 1,
            "comment": "some comment updated",
            "service_date": "04-01-2018",
            "service_time": "13:30",
            "master_id": 1,
            "client_id": 1,
            "procedures": [
              {
                "id": 1,
                "procedure_name": "shellac"
              }
            ],
            "client_detales": {
              "name": "sergii gary slon master",
              "phones": "0962377850",
              "photo": null
            }
          }
        ]
      },
      "4": {
        "service_date": "05-01-2018",
        "workitems": []
      },
      "5": {
        "service_date": "06-01-2018",
        "workitems": []
      },
      "6": {
        "service_date": "07-01-2018",
        "workitems": []
      }
    };
    const sch2 = {
      "0": {
        "service_date": "01-01-2018",
        "workitems": [
          {
            "id": 2,
            "comment": "some comment",
            "service_date": "03-01-2018",
            "service_time": "10:00",
            "master_id": 1,
            "client_id": 1,
            "procedures": [
              {
                "id": 1,
                "procedure_name": "shellac"
              }
            ],
            "client_detales": {
              "name": "sergii gary slon master",
              "phones": "0962377850",
              "photo": null
            }
          },
          {
            "id": 3,
            "comment": "some comment",
            "service_date": "03-01-2018",
            "service_time": "10:00",
            "master_id": 1,
            "client_id": 1,
            "procedures": [
              {
                "id": 1,
                "procedure_name": "shellac"
              }
            ],
            "client_detales": {
              "name": "sergii gary slon master",
              "phones": "0962377850",
              "photo": null
            }
          }]
      },
      "1": {
        "service_date": "02-01-2018",
        "workitems": []
      },
      "2": {
        "service_date": "03-01-2018",
        "workitems": [
          {
            "id": 2,
            "comment": "some comment",
            "service_date": "03-01-2018",
            "service_time": "10:00",
            "master_id": 1,
            "client_id": 1,
            "procedures": [
              {
                "id": 1,
                "procedure_name": "shellac"
              }
            ],
            "client_detales": {
              "name": "sergii gary slon master",
              "phones": "0962377850",
              "photo": null
            }
          },
          {
            "id": 3,
            "comment": "some comment",
            "service_date": "03-01-2018",
            "service_time": "10:00",
            "master_id": 1,
            "client_id": 1,
            "procedures": [
              {
                "id": 1,
                "procedure_name": "shellac"
              }
            ],
            "client_detales": {
              "name": "sergii gary slon master",
              "phones": "0962377850",
              "photo": null
            }
          }
        ]
      },
      "3": {
        "service_date": "04-01-2018",
        "workitems": [
          {
            "id": 1,
            "comment": "some comment updated",
            "service_date": "04-01-2018",
            "service_time": "13:30",
            "master_id": 1,
            "client_id": 1,
            "procedures": [
              {
                "id": 1,
                "procedure_name": "shellac"
              }
            ],
            "client_detales": {
              "name": "sergii gary slon master",
              "phones": "0962377850",
              "photo": null
            }
          }
        ]
      },
      "4": {
        "service_date": "05-01-2018",
        "workitems": []
      },
      "5": {
        "service_date": "06-01-2018",
        "workitems": []
      },
      "6": {
        "service_date": "07-01-2018",
        "workitems": []
      }
    };
    return this.props.masterid == 1 ? sch1 : sch2;
  }

  render() {
    const schedule = this.getScheduleFromServer();
    const masters = this.props.masters;
    const days = ["Понедельник", "Вторник", "Среда", "Четверг", "Пятница",
      "Суббота", "Воскресенье"];
    console.log("Week#render offset:" + this.state.offset);
    return (
        <div>
          <input type="button" value={"Prev"} onClick={this.getPrevWeek}/>
          <input type="button" value={"Next"} onClick={this.getNextWeek}/>
          {days.map(
              (day, i) => <Day key={i}
                               day={day}
                               date={schedule[i].service_date}
                               workitems={schedule[i].workitems}
                               masters={masters}/>
          )}
        </div>
    )
  }
}