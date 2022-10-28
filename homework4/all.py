import os

with open("all.txt", "w", encoding="utf-8") as w:
    for package in ["src"]:  # , "web"]:
        for name in os.popen(f"dir {package} /b").read().split("\n"):
            if "." in name:
                with open(f"{package}/{name}", "r", encoding="utf-8") as f:
                    w.write(f'/******************* "{package}\\{name}" ********************/\n\n')
                    w.write(f.read() + "\n")
