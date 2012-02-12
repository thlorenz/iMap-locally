pushd .

cd IMAPClient/src/com/imap/thl

# Compile
javac *.java

echo "Compiled, starting up"

# Run from src folder to resolve classes correctly
cd ../../../

java com/imap/thl/Main

# Clenaup
rm com/imap/thl/*.class

popd
