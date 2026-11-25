# DSA-PROJECT-
# 🅿️ Parking Lot Manager

A Java console application that manages a **single-lane parking lot with 3 slots** using core Data Structures: **Stack, Queue, Array and HashMap**, with file-based data storage.

![Java](https://img.shields.io/badge/Java-21-orange)
![Type](https://img.shields.io/badge/Type-Console%20Application-blue)
![Subject](https://img.shields.io/badge/Subject-DSA-green)

> **Project:** Parking Lot Manager (PS No. 10)
> **Course:** Data Structures and Algorithms (DSA), Course Code RU-200-01-00029
> **University:** Rungta International Skills University, Bhilai, CG
> **Session:** 2026-27

---

## 📌 Overview

In a single-lane parking lot, vehicles stand one behind the other, so only the vehicle that entered last can leave first. This project models that behaviour with a **Stack (LIFO)**. When the lot is full, new vehicles wait in a **Queue (FIFO)** and are parked automatically when a slot becomes free. A **HashMap** gives quick vehicle search and blocks duplicate vehicle numbers. Records are loaded from and saved to `parking.txt`, so data is not lost after the program closes.

## ✨ Features

- Park a vehicle (automatic slot number)
- Exit a vehicle (only the top vehicle of the stack can leave)
- Automatic parking of the first waiting vehicle when a slot becomes free
- Search a vehicle (parked / waiting / not found)
- Display Slot 1, Slot 2, Slot 3 and the waiting count
- Duplicate vehicle numbers are rejected
- Loads data from `parking.txt` at start and saves it on exit

## 🧱 Data Structures Used

| Concept | Purpose | Example |
|---|---|---|
| **Stack** | Parked vehicles (LIFO) | CG04C3 parked last, so it exits first |
| **Queue** | Waiting vehicles (FIFO) | CG04D4 waits first, so it parks first |
| **Array** | The three parking slot positions | `slots[0]`, `slots[1]`, `slots[2]` |
| **HashMap** | Vehicle lookup and duplicate checking | CG04Z9 → not found |
| **Methods** | Separate operations | park, exit, search, display |
| **File Handling** | Load and save records | `parking.txt` |

## 🗂️ Project Structure

```
parking-lot-manager-java/
├── Main.java              # Complete program (one file, one Main class)
├── README.md
├── .gitignore
└── docs/
    ├── Parking_Lot_Manager_Report.pdf
    └── Parking_Lot_Manager_PPT.pptx
```

`Main.java` contains a single class, `Main`, with these methods:
`loadData()`, `saveData()`, `parkVehicle()`, `exitVehicle()`, `searchVehicle()`, `displayParking()`, `validateVehicle()`, `refreshSlots()` and `main()`.

## 🚀 How to Run

**Requirements:** JDK 11 or higher (JDK 21 recommended).

```bash
# 1. Clone the repository
git clone https://github.com/<Narayanbhaikaumarya-00>/parking-lot-manager-java.git
cd parking-lot-manager-java

# 2. Compile
javac Main.java

# 3. Run
java Main
```

On the first run `parking.txt` does not exist, so the lot starts empty. The file is created when you choose option 5.

## 🖥️ Menu

```
1. Park vehicle
2. Exit vehicle
3. Search vehicle
4. Display
5. Save & Exit
```

## 💾 Data File Format (`parking.txt`)

One record per line: a letter, a comma and the vehicle number.

```
P,CG04A1
P,CG04B2
P,CG04C3
W,CG04D4
```

- `P` = parked vehicle (written from bottom of the stack to the top)
- `W` = waiting vehicle (written from front of the queue to the back)

## ✅ Official Test Cases (PS No. 10)

| # | Scenario | Input | Expected Output |
|---|---|---|---|
| 1 | Normal input | Option 1, vehicle `CG04A1` | `Vehicle CG04A1 parked. Slot: 1` |
| 2 | Boundary (lot full) | 3 slots full, option 1, vehicle `CG04D4` | `Lot full. Vehicle added to waiting queue. Position: 1` |
| 3 | Invalid input | Option 3, vehicle `CG04Z9` | `Vehicle CG04Z9 not found.` |
| 4a | Valid scenario – exit | Slots CG04A1 / CG04B2 / CG04C3, waiting CG04D4; option 2, vehicle `CG04C3` | `CG04C3 exited.` `CG04D4 parked from queue.` `Slot: 3` |
| 4b | Valid scenario – display | Option 4 | `Slot 1: CG04A1` `Slot 2: CG04B2` `Slot 3: CG04D4` `Waiting: 0` |

All four test cases were run on the program and the output matched the expected output.

> **Tip:** delete any old `parking.txt` before testing, so the program starts with an empty lot.

### Sample Session

```
Vehicle CG04A1 parked. Slot: 1
Vehicle CG04B2 parked. Slot: 2
Vehicle CG04C3 parked. Slot: 3
Lot full. Vehicle added to waiting queue. Position: 1
Only the top vehicle CG04C3 can exit.        <- CG04A1 tried to exit
CG04C3 exited.
CG04D4 parked from queue.
Slot: 3
Slot 1: CG04A1
Slot 2: CG04B2
Slot 3: CG04D4
Waiting: 0
Vehicle CG04A1 already exists.               <- duplicate rejected
```

## ⏱️ Time Complexity

| Operation | Complexity |
|---|---|
| Park (push) / Exit (pop) | O(1) |
| Queue offer / poll | O(1) |
| Search / duplicate check (HashMap) | O(1) average |
| Remove a waiting vehicle from the queue | O(W), W = waiting vehicles |
| Load / save file | O(V + W) |

## ⚠️ Notes and Limitations

- Fixed capacity of 3 slots and a single lane (only the top vehicle can exit).
- Data is stored in a plain text file, not a database.
- Vehicle number format is **not** strictly validated, because the official test cases use short values (CG04A1, CG04B2, ...) while the description gives CG04AB1234 as an example. Only empty and duplicate numbers are rejected.

## 🔮 Future Scope

- Graphical User Interface
- Database integration
- Real-time parking availability
- QR-based vehicle entry and exit

## 👨‍🎓 Author

**Narayan Kaumarya**
B.Tech in Computer Science & Engineering (AI & ML) in association with Google
School of Computer Science and Engineering, Rungta International Skills University, Bhilai, CG

**Under the guidance of:** Mr. Surya Mani Verma, Technical Trainer

## 📚 References

- Project Description, PS No. 10 – Parking Lot Manager
- Oracle Java documentation: https://docs.oracle.com/en/java/
- Goodrich, Tamassia and Goldwasser, *Data Structures and Algorithms in Java*
